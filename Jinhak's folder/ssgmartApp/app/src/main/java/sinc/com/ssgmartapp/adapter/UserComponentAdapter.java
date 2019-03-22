package sinc.com.ssgmartapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import sinc.com.ssgmartapp.R;
import sinc.com.ssgmartapp.dto.UserVO;
import sinc.com.ssgmartapp.helper.Util;

public class UserComponentAdapter extends ArrayAdapter<UserVO> {

    private Context context;

    public UserComponentAdapter(Context context, int textViewResourceId, List<UserVO> list) {
        super(context, textViewResourceId, list);
        this.context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Util.setGlobalFont(context, convertView);

        View curView = convertView;
        if (curView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            curView = vi.inflate(R.layout.user_component_item, null);
        }

        UserVO userVO = getItem(position);
        TextView userName = curView.findViewById(R.id.user_name);
        TextView userPhone = curView.findViewById(R.id.user_phone);

        userName.setText(userVO.getUser_Name());
        userPhone.setText(userVO.getUser_Phone());

        return curView;
    }
}
