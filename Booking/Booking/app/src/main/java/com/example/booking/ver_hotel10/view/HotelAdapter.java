package com.example.booking.ver_hotel10.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking.R;
import com.example.booking.entities.Hotel;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    private List<Hotel> hotelList;

    public HotelAdapter(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.templatehotel10, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Hotel hotel = hotelList.get(position);
        holder.textViewIdHotel.setText(String.valueOf(hotel.getId_Hotel()));
        holder.textViewNombreHotel.setText(hotel.getNombre_Hotel());
        holder.textViewReservasHotel.setText(String.valueOf(hotel.getReservas_Hotel()));
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }

    public static class HotelViewHolder extends RecyclerView.ViewHolder {
        TextView textViewIdHotel;
        TextView textViewNombreHotel;
        TextView textViewReservasHotel;

        public HotelViewHolder(View itemView) {
            super(itemView);
            textViewIdHotel = itemView.findViewById(R.id.textViewIdHotel);
            textViewNombreHotel = itemView.findViewById(R.id.textViewNombreHotel);
            textViewReservasHotel = itemView.findViewById(R.id.textViewReservasHotel);
        }
    }
}
