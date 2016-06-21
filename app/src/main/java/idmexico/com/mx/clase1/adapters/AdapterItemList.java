package idmexico.com.mx.clase1.adapters;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import Model.ModelItem;
import idmexico.com.mx.clase1.R;

/**
 * Created by Alumno on 11/06/2016.
 */
public class AdapterItemList extends ArrayAdapter<ModelItem> {

    private final String url_1="http://www.panacom.com.tw/files/ProductData/PD_Pic1/PASE-LOGO-300x200.gif";
    private final String url_2="http://laeconomia.com.mx/wp-content/uploads/pase-urbano.png";

    public AdapterItemList(Context context, List<ModelItem> objects) {
        super(context, 0 , objects);


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null) {

            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list,parent,false);

        }
        TextView txtitemdescription= (TextView) convertView.findViewById(R.id.txtdescripcion);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtitem);
        ImageView img= (ImageView) convertView.findViewById(R.id.imgrow);

        ModelItem modelItem = getItem(position);
        Picasso.with(getContext()).load(modelItem.resourceID==R.drawable.ic_action_account_circle ? url_1 :url_2).into(img);


        txtTitle.setText(modelItem.item);
        txtitemdescription.setText(modelItem.description);
        img.setImageResource(modelItem.resourceID);


        return convertView;
    }
}
