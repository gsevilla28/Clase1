package idmexico.com.mx.clase1;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import Model.ModelItem;
import idmexico.com.mx.clase1.adapters.AdapterItemList;
import idmexico.com.mx.clase1.sql.ItemDataSource;
import idmexico.com.mx.clase1.sql.MySqliteHelper;

/**
 * Created by Alumno on 11/06/2016.
 */
public class FragmentList extends Fragment implements View.OnLongClickListener, AdapterView.OnItemLongClickListener {

    private ListView list;
    //private List<ModelItem> array = new ArrayList<>();
    private boolean isWifi= false;
    private int counter=0;
    private ItemDataSource itemDataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemDataSource = new ItemDataSource(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);

        list = (ListView) view.findViewById(R.id.listitem);
        final EditText texto = (EditText) view.findViewById(R.id.txtlist);

        //list.setOnLongClickListener(this);
        list.setOnItemLongClickListener(this);


        list.setAdapter(new AdapterItemList(getActivity(),itemDataSource.getAllItems()));

        view.findViewById(R.id.btnaddlist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemdata= texto.getText().toString();

                if (!TextUtils.isEmpty(itemdata))
                {
                    ModelItem item = new ModelItem();
                    item.item=itemdata;
                    item.description= "a" +counter;
                    item.resourceID = isWifi? R.drawable.ic_action_account_circle: R.mipmap.ic_launcher;
                    //array.add(item);
                    itemDataSource.saveItem(item); /*guardar en base de datos local*/

                    list.setAdapter(new AdapterItemList(getActivity(),itemDataSource.getAllItems()));
                    isWifi=!isWifi;
                    counter++;
                }
            }
        });

        return view;
    }

    @Override
    public boolean onLongClick(View v) {
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.delete_title)
                .setMessage("¿Deseas Borrar el Elemento?")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();

        return false;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        AdapterItemList adapter = (AdapterItemList) parent.getAdapter();
        final ModelItem modelItem = adapter.getItem(position);


        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.delete_title)
                .setMessage(String.format("¿Deseas Borrar el Elemento %s?", modelItem.item))
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        itemDataSource.deleteItem(modelItem); /*metodo para eliminar un registro*/

                        list.setAdapter(new AdapterItemList(getActivity(),/*obtener todos los elementos nuevamente*/
                                itemDataSource.getAllItems()));

                    }
                }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setCancelable(false).create().show();

        return true;
    }
}
