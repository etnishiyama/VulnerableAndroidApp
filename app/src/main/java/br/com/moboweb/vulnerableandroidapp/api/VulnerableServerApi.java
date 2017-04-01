package br.com.moboweb.vulnerableandroidapp.api;

import java.util.List;

import br.com.moboweb.vulnerableandroidapp.data.LoginModel;
import br.com.moboweb.vulnerableandroidapp.data.UserModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author Everton Takashi Nishiyama <etnishiyama@gmail.com>
 * @version 1.0
 * @since 3/31/17
 */
public interface VulnerableServerApi {
        @GET("/app/v1/user")
        Call<List<UserModel>> getUsers();

        @POST("/app/v1/user/authenticate")
        Call<LoginModel> userLogin(@Body UserModel user);
}
