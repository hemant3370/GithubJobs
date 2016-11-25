package githubjobs.georgianhemant.com.githubjobs.Retrofit.Interceptors;


import java.io.IOException;

import githubjobs.georgianhemant.com.githubjobs.Applications.Initializer;
import githubjobs.georgianhemant.com.githubjobs.Utils.ConnectivityUtil;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by HemantSingh on 21/11/16.
 */

public class CachingControlInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        // Add Cache Control only for GET methods
        if (request.method().equals("GET")) {
            if (new ConnectivityUtil(Initializer.getAppContext()).connected()) {
                // 1 day
                request = request.newBuilder()
                        .header("Cache-Control", "only-if-cached")
                        .build();
            } else {
                // 4 weeks stale
                request = request.newBuilder()
                        .header("Cache-Control", "public, max-stale=2419200")
                        .build();
            }
        }

        Response originalResponse = chain.proceed(request);
        return originalResponse.newBuilder()
                .header("Cache-Control", "max-age=600")
                .build();
    }
}
