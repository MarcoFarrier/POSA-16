package vandy.mooc;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.File;

import static vandy.mooc.common.BitmapUtils.grayScaleFilter;

/**
 * Created by Marco on 2016-04-10.
 */
public class FilterAsyncTask extends AsyncTask<Uri, Void, Uri> {

    Context mContext;

    public FilterAsyncTask(Context myContext)
    {
        mContext = myContext;
    }

    @Override
    protected Uri doInBackground(Uri... uris) {
        Uri pathToImageFile = uris[0];
        Uri directoryPathname = uris[1];

        Uri toReturn = grayScaleFilter(mContext, pathToImageFile, directoryPathname);

        File f = new File(String.valueOf(pathToImageFile));
        f.delete();

        return toReturn;
    }
}
