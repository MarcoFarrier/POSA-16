package vandy.mooc;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import java.util.ArrayList;

import vandy.mooc.model.ImageDownloadsModel;

import static vandy.mooc.common.BitmapUtils.grayScaleFilter;

/**
 * Created by Marco on 2016-04-09.
 */
public class DownloadAsyncTask  extends AsyncTask<Uri, Void, Uri> {

    protected Context mContext;

    public DownloadAsyncTask(Context contextToUse)
    {
        mContext = contextToUse;
    }

    @Override
    protected Uri doInBackground(Uri... uris) {

        ImageDownloadsModel idm = new ImageDownloadsModel();

        Uri url = uris[0];
        Uri pathToFile = uris[1];

        return idm.downloadImage(mContext, url, pathToFile);
    }
}
