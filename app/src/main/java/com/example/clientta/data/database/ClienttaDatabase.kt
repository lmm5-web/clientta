package com.example.clientta.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.clientta.data.dao.AgendamentoDao
import com.example.clientta.data.dao.ClienteDao
import com.example.clientta.data.dao.PreAtendimentoDao
import com.example.clientta.data.dao.ProfissionalDao
import com.example.clientta.data.dao.TratamentoDao
import com.example.clientta.data.entity.Agendamento
import com.example.clientta.data.entity.Cliente
import com.example.clientta.data.entity.PreAtendimento
import com.example.clientta.data.entity.Profissional
import com.example.clientta.data.entity.Tratamento
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        Cliente::class,
        Profissional::class,
        Tratamento::class,
        Agendamento::class,
        PreAtendimento::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ClienttaDatabase : RoomDatabase() {

    abstract fun clienteDao(): ClienteDao
    abstract fun profissionalDao(): ProfissionalDao
    abstract fun tratamentoDao(): TratamentoDao
    abstract fun agendamentoDao(): AgendamentoDao
    abstract fun preAtendimentoDao(): PreAtendimentoDao

    companion object {
        @Volatile
        private var INSTANCE: ClienttaDatabase? = null

        fun getDatabase(context: Context): ClienttaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ClienttaDatabase::class.java,
                    "clientta_database"
                )
                .addCallback(DatabaseCallback(context.applicationContext))
                .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback(
            private val context: Context
        ) : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        populateInitialData(database)
                    }
                }
            }
        }

        private suspend fun populateInitialData(db: ClienttaDatabase) {
            // Tratamentos iniciais conforme especificações do PRD
            val tratamentosIniciais = listOf(
                Tratamento(
                    nome = "Limpeza de Pele Profunda",
                    descricao = "Remoção de impurezas, cravos e células mortas com higienização, esfoliação e hidratação.",
                    preco = 120.0,
                    duracao = 60,
                    ativo = true
                ),
                Tratamento(
                    nome = "Massagem Relaxante",
                    descricao = "Massagem corporal com óleos essenciais para alívio do estresse e tensão muscular.",
                    preco = 150.0,
                    duracao = 50,
                    ativo = true
                ),
                Tratamento(
                    nome = "Drenagem Linfática",
                    descricao = "Técnica de massagem que estimula o sistema linfático, reduzindo a retenção de líquidos.",
                    preco = 130.0,
                    duracao = 60,
                    ativo = true
                ),
                Tratamento(
                    nome = "Peeling Diamantado",
                    descricao = "Microdermoabrasão superficial para renovação celular e diminuição de manchas e linhas finas.",
                    preco = 180.0,
                    duracao = 45,
                    ativo = true
                )
            )
            db.tratamentoDao().insertAll(tratamentosIniciais)

            // Profissional padrão para testes da área profissional
            val profissionalPadrao = Profissional(
                nome = "Dra. Ana Silva",
                email = "profissional@clientta.com",
                telefone = "(81) 99999-8888",
                especialidade = "Esteticista Facial e Corporal",
                senha = "admin"
            )
            db.profissionalDao().insert(profissionalPadrao)
        }
    }
}
