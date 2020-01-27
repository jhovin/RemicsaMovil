package pe.bonifacio.pasapp.api;

import java.util.List;

import pe.bonifacio.pasapp.models.Mina;
import pe.bonifacio.pasapp.models.Proyecto;
import pe.bonifacio.pasapp.models.Superficie;
import pe.bonifacio.pasapp.models.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface WebServiceApi {

    String BASE_URL = "http://10.0.2.2:8040/";

    /*
       USUARIOS
        */
    @POST("/api/usuarios")
    Call<Void> registrarUsuario(@Body Usuario usuario);

    @POST("/api/login")
    Call<List<Usuario>> login(@Body Usuario usuario);

    @DELETE("api/usuarios/{id}")
    Call<Void> deleteById(@Path("id") Long id);

    @PUT("api/update_sql")
    Call<Usuario> update(@Body Usuario usuario);

    @GET("api/usuarios")
    Call<List<Usuario>> getUsuarios();

    /*
    Proyectos
     */
    @POST("api/proyectos")
    Call<Void> crearProyecto(@Body Proyecto proyecto);

    @GET("api/proyectos")
    Call<List<Proyecto>> getTodosLosProyectos();

    @POST("api/proyectos_usuario")
    Call<List<Proyecto>> getProyectosUsuarios(@Body Usuario usuario);



    /*
    MAQUINAS MINAS
     */
    @POST("api/minas")
    Call<Void> crearMina(@Body Mina mina);

    @GET("api/minas")
    Call<List<Mina>> getTodosLosMinas();

    @POST("api/minas_proyecto")
    Call<List<Mina>>getMinasProyectos(@Body Proyecto proyecto);

    /*
   MAQUINAS SUPERFICIE
    */
    @POST("api/superficies")
    Call<Void> crearSuperficie(@Body Superficie superficie);

    @GET("api/superficies")
    Call<List<Superficie>> getTodosLosSuperficies();

    @POST("api/superficies_proyecto")
    Call<List<Superficie>> getSuperficiesProyectos(@Body Proyecto proyecto);


}
