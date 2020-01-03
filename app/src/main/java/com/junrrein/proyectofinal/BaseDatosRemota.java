package com.junrrein.proyectofinal;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

class BaseDatosRemota {

    private static final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private static final String nodoUsuarios = "usuarios";

    static private Task<Boolean> existeNodo(String nodo) {
        TaskCompletionSource<Boolean> taskCompletionSource = new TaskCompletionSource<>();

        database.child(nodo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                taskCompletionSource.setResult(dataSnapshot.exists());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                taskCompletionSource.setException(databaseError.toException());
            }
        });

        return taskCompletionSource.getTask();
    }

    static Task<Boolean> existeUsuario(String id) {
        return existeNodo(nodoUsuarios + "/" + id);
    }

    static Task<Void> crearUsuario(String id, Usuario usuario) {
        return existeUsuario(id)
                .continueWith(task -> {
                    Boolean existe = task.getResult();

                    if (existe)
                        throw new Exception("El usuario ya existe");

                    database.child(nodoUsuarios).child(id).setValue(usuario);
                    return null;
                });
    }

    static Task<Void> actualizarUsuario(String id, Usuario usuario) {
        return database.child(nodoUsuarios).child(id).updateChildren(usuario.toMap());
    }

    static Task<Void> eliminarUsuario(String id) {
        return database.child(nodoUsuarios).child(id).removeValue();
    }
}
