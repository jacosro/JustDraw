package com.jacosro.justdraw;

import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jacosro.justdraw.entities.Drawing;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawingAdapter extends RecyclerView.Adapter<DrawingAdapter.DrawingHolder> {

    private SortedList<Drawing> drawings;

    public static class DrawingHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView nameTextView;

        @BindView(R.id.date)
        TextView dateTextView;

        public DrawingHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(itemView);
        }
    }

    public DrawingAdapter() {
        drawings = new SortedList<>(Drawing.class, new SortedList.Callback<Drawing>() {
            @Override
            public int compare(Drawing drawing1, Drawing drawing2) {
                return drawing1.getDate().compareTo(drawing2.getDate());
            }

            @Override
            public void onChanged(int i, int i1) {
                notifyItemRangeChanged(i, i1);
            }

            @Override
            public boolean areContentsTheSame(Drawing drawing, Drawing t21) {
                return drawing.equals(t21);
            }

            @Override
            public boolean areItemsTheSame(Drawing drawing, Drawing t21) {
                return drawing == t21;
            }

            @Override
            public void onInserted(int i, int i1) {
                notifyItemRangeInserted(i, i1);
            }

            @Override
            public void onRemoved(int i, int i1) {
                notifyItemRangeRemoved(i, i1);
            }

            @Override
            public void onMoved(int i, int i1) {
                notifyItemMoved(i, i1);
            }
        });
    }

    public void add(Drawing drawing) {
        drawings.add(drawing);
    }

    public void remove(Drawing drawing) {
        drawings.remove(drawing);
    }

    public void clear() {
        drawings.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DrawingHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.drawing, viewGroup);
        return new DrawingHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DrawingHolder drawingHolder, int i) {
        drawingHolder.nameTextView.setText(drawings.get(i).getName());
        drawingHolder.dateTextView.setText(drawings.get(i).getDate().toString());
    }

    @Override
    public int getItemCount() {
        return drawings.size();
    }

}
