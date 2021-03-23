package com.example.bloodbank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdp extends SliderViewAdapter<SliderAdp.ViewHolder> {


        int[] images;

    public SliderAdp(int[] images) {
        this.images = images;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imgslider,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.img.setImageResource(images[position]);
    }

    @Override
    public int getCount() {
        return images.length;
    }

        public class ViewHolder extends SliderViewAdapter.ViewHolder{

        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            img= itemView.findViewById(R.id.imgslid);
        }
    }
}
//}

//    int[] images;
//
//    public SliderAdp(int[] images) {
//        this.images = images;
//    }
//
//    @Override
//    public SliderViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent) {
//
//        View view = LayoutInflater.from(parent.getContext()).inflate((R.layout.imgslider,parent,false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(SliderViewAdapter.ViewHolder viewHolder, int position) {
//
//
//
//    }
//
//    @Override
//    public int getCount() {
//        return 0;
//    }
//
//    public class ViewHolder extends SliderViewAdapter.ViewHolder{
//
//        ImageView img;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            img= itemView.findViewById(R.id.imgslid);
//        }
//    }
//}
