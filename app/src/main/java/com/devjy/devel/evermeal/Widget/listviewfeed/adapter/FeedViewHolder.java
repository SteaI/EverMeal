package com.devjy.devel.evermeal.Widget.listviewfeed.adapter;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.devjy.devel.evermeal.Widget.listviewfeed.FeedImageView;

/**
 * Created by devel on 2016-07-19.
 */
public class FeedViewHolder
{
    public TextView name;
    public TextView timestamp;
    public TextView statusMsg;
    public TextView url;
    public NetworkImageView profilePic;
    public TextView profileView;
    public FeedImageView feedImageView;
    public Button btnRate;
    public Button btnReview;
    public ListView commentList;
}
