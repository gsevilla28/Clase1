package idmexico.com.mx.clase1;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.nio.BufferUnderflowException;

/**
 * Created by Alumno on 11/06/2016.
 */
public class FragmentProfile extends Fragment{

    /*en fragment no hay comunicacion por intent*/

    public static FragmentProfile newInstance(String name){
        FragmentProfile f = new FragmentProfile();
        Bundle b = new Bundle();
        b.putString("key_user",name);
        f.setArguments(b);
        return  f;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        ImageView imgprofile = (ImageView) view.findViewById(R.id.improfile);
        TextView txt = (TextView) view.findViewById(R.id.txtuserFragment);
        Bundle b= getArguments();

        String user ;
        if (b!=null) {
            user = b.getString("key_user");
        }
        else {
            user = "XML inflate";
        }
        txt.setText(user);


        return view;
    }
}
