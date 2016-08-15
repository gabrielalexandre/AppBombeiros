package sos.bombeiro.appbombeiros;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Gabriel on 06/08/2016.
 */
public class GPS implements LocationListener{
    protected static final String TAG = null;
    private Context context;
    private LocationManager lm;
    private Location location;
    private volatile boolean stop = false;
    private static final int UM_SEGUNDO = 1000;
    private int tempoTotalBusca = 10;
    protected ProgressDialog progressDialog;

    public GPS(Context context) {
        lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        this.context = context;
    }

    public boolean estado() {
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public Location capturarCoordenadaGPS() {

        try {
            new Thread(new Runnable() {
                public void run() {
                    Looper.myLooper();
                    Looper.prepare();

                    /*progressDialog = ProgressDialog.show(context, null,
                            context.getString(R.string.aguarde),
                            true);*/

                    ativaGPS();
                    Looper.loop();
                }
            }).start();
            // Thread.sleep(10*1000);

            int tempoBusca = 0;

            while (!stop) {
                if (tempoTotalBusca == tempoBusca) {
                    break;
                }

                Thread.sleep(UM_SEGUNDO);
                tempoBusca++;
            }
            return location;
        } catch (Exception e) {
            // TODO - Trate a exceção;
        } finally {
            desativaGPS();
            if (progressDialog != null)
                progressDialog.dismiss();
        }
        return null;
    }

    public void ativaGPS() {
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this,
                Looper.myLooper());
        // Looper.loop();
    }

    private void desativaGPS() {
        lm.removeUpdates(GPS.this);
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
        stop = true;
    }

    @Override
    public void onProviderDisabled(String provider) {
        // Provider desabilitado
    }

    @Override
    public void onProviderEnabled(String provider) {
        // Provider habilitado
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Status do provider alterado
    }

}

