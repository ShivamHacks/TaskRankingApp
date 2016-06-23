package com.example.shivamagrawal.taskranker2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CustomCardViewList {

    CardViewAdapter adapter;

    public CustomCardViewList(List<Task> tasks, OnItemTouchListener onItemTouchListener) {
        adapter = new CardViewAdapter(tasks, onItemTouchListener);
    }

    /**
     * Interface for the touch events in each item
     */
    public interface OnItemTouchListener {
        /**
         * Callback invoked when the user Taps one of the RecyclerView items
         *
         * @param view     the CardView touched
         * @param position the index of the item touched in the RecyclerView
         */
        void onCardViewTap(View view, int position);
    }

    /**
     * A simple adapter that loads a CardView layout with one TextView and two Buttons, and
     * listens to clicks on the Buttons or on the CardView
     */
    public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {
        private List<Task> tasks;
        private OnItemTouchListener onItemTouchListener;

        public CardViewAdapter(List<Task> tasks, OnItemTouchListener onItemTouchListener) {
            this.tasks = tasks;
            this.onItemTouchListener = onItemTouchListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_layout, viewGroup, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.taskTitle.setText(tasks.get(i).getTask());
        }

        @Override
        public int getItemCount() {
            return tasks == null ? 0 : tasks.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView taskTitle;

            public ViewHolder(View itemView) {
                super(itemView);
                taskTitle = (TextView) itemView.findViewById(R.id.card_view_task);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemTouchListener.onCardViewTap(v, getLayoutPosition());
                    }
                });
            }
        }
    }

}
