package logica;

import entidades.Usuario;

public class Context {
    private static Context instance;
    private Usuario usuario;
    
    private Context() {
    }
    
    public static Context getInstance(){
        if(instance == null) instance = new Context();
        return instance;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
