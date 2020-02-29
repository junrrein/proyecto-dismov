package com.junrrein.proyectofinal;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.Instant;

@Entity(tableName = "usuarios")
class UsuarioRoom {

    @NonNull
    @PrimaryKey
    String id = "";

    String nombreApellido;
    String idEventosCreados;
    String idEventosInteresado;
    String idEventosAsiste;
    long ultimaActualizacion;

    UsuarioRoom() {
    }

    UsuarioRoom(Usuario usuario) {
        id = usuario.getId();
        nombreApellido = usuario.getNombreApellido();
        idEventosCreados = String.join(" ", usuario.getIdEventosCreados());
        idEventosInteresado = String.join(" ", usuario.getIdEventosInteresado());
        idEventosAsiste = String.join(" ", usuario.getIdEventosAsiste());
        ultimaActualizacion = Instant.now().getEpochSecond();
    }
}
