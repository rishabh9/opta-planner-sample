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

    public static final String API_KEY = "AIzaSyCkjTXp7I6gaA0zg5iJpX39nQw4oiYw77s";

    public static void main(String[] args) {

        String[] origins = {"12.9752265,77.7391331"};
        String[] destinations = {"12.9752265,77.7391331"};

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
