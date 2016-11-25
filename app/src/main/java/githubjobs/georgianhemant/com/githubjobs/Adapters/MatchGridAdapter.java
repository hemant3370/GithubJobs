package githubjobs.georgianhemant.com.githubjobs.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import githubjobs.georgianhemant.com.githubjobs.Models.PositionResponse;
import githubjobs.georgianhemant.com.githubjobs.R;


/**
 * Created by HemantSingh on 25/10/16.
 */

public class MatchGridAdapter extends RecyclerView.Adapter<MatchGridAdapter.ViewHolder> {

    private List<PositionResponse> mFileset;
    private final Context context;

    CustomItemClickListener listener;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mItemView;
        public ImageView imgageView;
        public TextView name,companyname,location;

        public ViewHolder(View v) {
            super(v);
            mItemView = v;
            imgageView = (ImageView) v.findViewById(R.id.ivProfile);
            name = (TextView) v.findViewById(R.id.nametv);
            companyname = (TextView) v.findViewById(R.id.companynametv);
            location = (TextView) v.findViewById(R.id.locationtv);
        }


    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MatchGridAdapter(Context context, List<PositionResponse> fileSet, CustomItemClickListener listener) {

        this.listener = listener;
        this.context = context;
        this.mFileset = fileSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MatchGridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view

            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.jobitem, parent, false);
            // set the view's size, margins, paddings and layout parameters

            final MatchGridAdapter.ViewHolder vh = new MatchGridAdapter.ViewHolder(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v, vh.getAdapterPosition());
                }
            });

            return vh;


    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.location.setText(nullCheck(mFileset.get(position).getLocation()));
            holder.name.setText(nullCheck(mFileset.get(position).getTitle()));
            holder.companyname.setText(nullCheck(mFileset.get(position).getCompany()));
          Glide.with(context).load(nullCheck(mFileset.get(position).getCompanyLogo())).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().fitCenter().listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                holder.imgageView.setImageDrawable(resource);
                return false;
            }
        })
                .into(holder.imgageView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return mFileset.size();
    }
public String nullCheck(String str){
    if (str != null) {
     return  str;
    }
    return "";
}

}



