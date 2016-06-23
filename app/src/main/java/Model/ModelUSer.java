package Model;

/**
 * Created by Alumno on 17/06/2016.
 */
public class ModelUSer {
    public int id_usuario;
    public String userName;
    public String password;
    public String Nombre;


    public ModelUSer(int idusuario ,String mUser, String mPassword,String mNombre) {
        this.id_usuario=idusuario;
        this.userName =mUser;
        this.password=mPassword;
        this.Nombre=mNombre;
    }
}
