package id.ac.ukdw.ti.a71190426_10_sqlite

import android.provider.BaseColumns

class DatabaseContract {
    object Penduduk : BaseColumns {
        const val TABLE_NAME = "penduduk"
        const val COLUMN_NAME_NAMA = "nama"
        const val COLUMN_NAME_USIA = "usia"

        const val SQL_CREATE_TABLE = "CREATE TABLE ${TABLE_NAME} ("+
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "${COLUMN_NAME_NAMA} TEXT, "+
                "${COLUMN_NAME_USIA} INTEGER) "

        const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ${TABLE_NAME}"
    }
}