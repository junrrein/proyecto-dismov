package com.junrrein.proyectofinal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.junrrein.proyectofinal.backend.Evento;
import com.junrrein.proyectofinal.backend.Repositorio;
import com.junrrein.proyectofinal.backend.Usuario;

import java.util.List;


public class ModeloUsuario extends ViewModel {

    public String idUsuario;

    void setUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
