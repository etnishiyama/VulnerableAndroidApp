package br.com.moboweb.vulnerableandroidapp.api;

import java.util.List;

import br.com.moboweb.vulnerableandroidapp.model.ClockModel;
import br.com.moboweb.vulnerableandroidapp.model.LoginModel;
import br.com.moboweb.vulnerableandroidapp.model.UserModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

        @GET("/app/v1/clock/{user_id}")
        Call<List<ClockModel>> getAllClockin(@Path("user_id") String user_id);

        @POST("/app/v1/clock/{user_id}")
        Call<ClockModel> sendClockin(@Path("user_id") String user_id, @Body String timestamp);
}
