package in.notwork;

import com.google.gson.Gson;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.Unit;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;

/**
 * Hello world!
 */
public class App {

    public static final String API_KEY = "";

    public static void main(String[] args) {

        String[] origins = {"12.92386,77.65312",
                "12.92603,77.67625",
                "12.87206,77.55859",
                "12.96899,77.61006",
                "12.96934,77.60854",
                "12.88021,77.64558",
                "13.09264,77.5862",
                "13.08792,77.58914",
                "13.0782,77.58725",
                "13.09445,77.58601"

        };
        String[] destinations = {"12.92386,77.65312",
                "12.92603,77.67625",
                "12.87206,77.55859",
                "12.96899,77.61006",
                "12.96934,77.60854",
                "12.88021,77.64558",
                "13.09264,77.5862",
                "13.08792,77.58914",
                "13.0782,77.58725",
                "13.09445,77.58601"

        };

        GeoApiContext context = new GeoApiContext().setApiKey(API_KEY);
        SocketAddress address = new InetSocketAddress("stbeproxy.symphonyteleca.com", 8080);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
        context.setProxy(proxy);
        DistanceMatrixApiRequest request = DistanceMatrixApi.getDistanceMatrix(context, origins, destinations);
        try {
            DistanceMatrix matrix = request.units(Unit.METRIC).await();
            Gson gson = new Gson();
            System.out.println(gson.toJson(matrix));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
