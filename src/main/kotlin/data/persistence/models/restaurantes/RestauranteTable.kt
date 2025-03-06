package com.example.data.persistence.models.restaurantes

import org.jetbrains.exposed.dao.id.IntIdTable
import java.awt.SystemColor.text

object RestauranteTable : IntIdTable("Restaurante") {
    val titulo = varchar("titulo", 100).uniqueIndex()
    val descripcion = text("descripcion")
    val imagen = varchar("imagen", 255)
}
