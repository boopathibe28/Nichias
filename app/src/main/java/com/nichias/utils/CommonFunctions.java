package com.nichias.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.nichias.R;
import com.nichias.activity.GRNProcess;
import com.nichias.activity.SplashScreenActivity;
import com.nichias.api.Urls;
import com.nichias.app_interfaces.CommonCallback;
import com.nichias.model.ErrorApiResponse;
import com.nichias.model_api.HomeViewApiResponse;
import com.nichias.realm_db.CartRealmModel;
import com.nichias.realm_db.RealmLibrary;
import com.nichias.utils._pref.SessionManager;
import com.nichias.utils._pref.SharedPrefConstants;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.onesignal.OneSignal;
import com.valdesekamdem.library.mdtoast.MDToast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.RealmResults;
import okhttp3.ResponseBody;

import static com.nichias.utils._pref.SharedPrefConstants.DEVICE_TOKEN;
import static com.nichias.utils._pref.SharedPrefConstants.LANGUAGE_CODE;

public class CommonFunctions {


    private static CommonFunctions ourInstance = new CommonFunctions();
    public static String RollNo = "";
    public static String RM_POKA_MOLD = "";
    public static String RM_POKA_MACHINE_NO = "";
    public static String DATA_TIME = "";
    public static String LotNo = "";
    public static String Size = "";
    public static String ManagedNo = "";

    public static String SERIAL_CODE = "";
    public static String SL_NO = "";
    public static String PART_NO = "";


    public static String TEMP_OTP = "";
    public static String DEVICE_TYPE = "1";
    public static String CURRENCY_CODE = "SAR";
    public static String DOC_NO = "";

    public static String OP_BATCH = "";

    // RM Inventory
    public static String ansCoilNo = "";
    public static String ansSize = "";
    public static String ansWeight = "";
    public static String BAR_CODE_VALUE = "";
    // Rm Grid Storage
    public static String ssiplID = "";
    public static String locationID = "";

    // HR Slitting - FG-Data
    public static String BAR_CODE_VALUE_FG = "";
    public static String ssiplID_FG = "";
    public static String coilNo_FG = "";
    public static String thick_FG = "";
    public static String width_FG = "";
    // Scrp Enter
    public static String WorkOrder_TRIMMED_SCRAP = "";

    public static String Mother_Coil_ID = "";
    public static String FG_SSIPL_ID = "";


    public static String FG_Scan_Batch_No = "";
    public static String FG_Scan_Batch_QTY = "";
    public static String FG_Scan_T_W_L = "";
    public static String FG_Scan_Thick = "";
    public static String FG_Scan_Width = "";
    public static String FG_Scan_Length= "";
    public static String FG_Scan_Grade = "";

    public static String store_lat = "";
    public static String store_lng = "";
    public static String store_address = "";

    public static String dateFormat_dd_MM_yyyy = "dd-MM-yyyy";
    public static String dateFormat_MMM_dd_yyyy = "MMM dd, yyyy";
    public static String dateFormat_dd_MM_yyyy_HH_mm = "dd-MM-yyyy HH:mm";
    public static String dateFormat_EEE_dd_MM_yyyy_HH_mm = "EEE, MMM dd, yyyy";

    public static String MAP_KEY = "AIzaSyCiV7HmmQX4Q-HVyzgGfxZ7v5xs29zy-no";
    public static String MAP_KEY_PATH = "AIzaSyAoBimyGS5412B53_5GM05__J7UoiVDuJg";

    public static List<HomeViewApiResponse.Banner> simPageBanner;
    public static String selectionAddress = "";
    public static String checkoutSelectedAddress = "";
    public static String selectionLat = "";
    public static String selectionLon = "";

    public static String FROM_CART_PAGE = "";
    public static String temp_BRANCH_KEY = "";
    public static String temp_MINORDER = "";
    public static String temp_PAYMENTOPTION = "";
    public static String temp_DELIVERYOPTION = "";

    public static String orderFrom = "";
    public static String profileImage = "";

    private CommonFunctions() {
    }

    public static CommonFunctions getInstance() {
        if (ourInstance == null) {
            synchronized (CommonFunctions.class) {
                if (ourInstance == null)
                    ourInstance = new CommonFunctions();
            }
        }
        return ourInstance;
    }

    public static void Onesignal(Context context) {
        /*OneSignal.setInFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification);

        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {
                SessionManager.getInstance().insertIntoPreference(context, DEVICE_TOKEN,userId);
                Log.d("debug", "User:" + userId);

                if (registrationId != null)
                    Log.d("debug", "registrationId:" + registrationId);
            }
        });*/
    }


    public boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * Hide actionbar from activity class
     *
     * @param actionbar
     */
    public void hideActionBar(ActionBar actionbar) {
        if (actionbar != null) {
            actionbar.hide();
        }
    }

    /**
     * @param context          Context fromactivity or fragment
     * @param bundle           Bundle of values for next Activity
     * @param destinationClass Destination Activity
     * @param isFinish         Current activity need to finish or not
     */
    public void newIntent(Context context, Class destinationClass, Bundle bundle, boolean isFinish, boolean isFlags) {
        if (!isActivityRunning(context)) {
            return;
        }
        Intent intent = new Intent(context, destinationClass);
        intent.putExtras(bundle);
        if (isFlags) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
        if (isFinish) {
            ((Activity) context).finish();
        }
    }

    public void newIntent(Context context, Class destinationClass, Bundle bundle) {
        if (!isActivityRunning(context)) {
            return;
        }
        Intent intent = new Intent(context, destinationClass);
        intent.putExtras(bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);

    }

    /**
     * new intent with result get called here
     *
     * @param context
     * @param destinationClass
     * @param bundle
     * @param isFinish
     * @param requestCode
     */
    public void newIntentForResult(Context context, Class destinationClass, Bundle bundle, boolean isFinish, int requestCode) {
        Intent intent = new Intent(context, destinationClass);
        intent.putExtras(bundle);
        ((Activity) context).startActivityForResult(intent, requestCode);
        if (isFinish) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            ((Activity) context).finish();
        }
    }

    public void EmptyField(Context context, String message) {
        String result = MessageFormat.format(LanguageConstants.CannotBeEmpty, message);
        Toast mdToast = Toast.makeText(context, result, Toast.LENGTH_LONG);
        mdToast.show();
    }

    /**
     * This method will help to find the
     * activity is running or not
     *
     * @param ctx
     * @return
     */
    public boolean isActivityRunning(Context ctx) {
        ActivityManager activityManager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(Integer.MAX_VALUE);

        for (ActivityManager.RunningTaskInfo task : tasks) {
            if (ctx.getPackageName().equalsIgnoreCase(task.baseActivity.getPackageName()))
                return true;
        }

        return false;
    }

    public static boolean CheckInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }

    public void displayKnownError(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public void displayUnKnownError(Context context) {
        Toast.makeText(context, LanguageConstants.somethingWentWrong, Toast.LENGTH_LONG).show();
    }

    /**
     * When the Api throw an error this function will call
     * <p>
     * *** Uses
     * * Dismiss Dialog
     * * Print stack
     * * Return error block
     * * Display error
     *
     * @param context
     * @param errorBody
     * @param listener
     */
    public void apiErrorConverter(Context context, Object errorBody, CommonCallback.Listener listener) {
        try {
            InputStream inputStream = ((ResponseBody) errorBody).byteStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            Gson gson = new GsonBuilder().create();
            ErrorApiResponse mError = gson.fromJson(bufferedReader.readLine(), ErrorApiResponse.class);
//            CommonFunctions.getInstance().displayKnownError(context, mError.getMessage());

           // validationError(context, mError.getMessage());

            listener.onFailure(mError.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
           // validationError(context, LanguageConstants.somethingWentWrong);
            listener.onFailure(LanguageConstants.somethingWentWrong);
        }
        CustomProgressDialog.getInstance().dismiss();
    }

    public void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)
                activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && activity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }


    public void hideSoftKeyboardNew(Activity activity) {
        activity.getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN );
    }

    // Mobile number validation
    public boolean isValidMobile(String mobile) {
        if (!TextUtils.isEmpty(mobile)) {
            return Patterns.PHONE.matcher(mobile).matches();
        }
        return false;
    }


    public static Spanned fromHtml(String html) {
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }


    // -------- Local Change Language
    public void refreshLocale(@NonNull Context context, boolean force) {
        // final String language = QuranSettings.getInstance(this).isArabicNames() ? "ar" : null;

        String language = null;
        if (SessionManager.getInstance().getFromPreference(LANGUAGE_CODE).isEmpty()){
            language = "";
        }
        else if (SessionManager.getInstance().getFromPreference(LANGUAGE_CODE).equals("en")){
            language = "en";
        }
        else if (SessionManager.getInstance().getFromPreference(LANGUAGE_CODE).equals("ar")){
            language = "ar";
        }
        final Locale locale;
        if ("ar".equals(language)) {
            locale = new Locale("ar");
        } else if (force) {
            // get the system locale (since we overwrote the default locale)
            locale = Resources.getSystem().getConfiguration().locale;
        } else {
            // nothing to do...
            locale = new Locale("en");
            return;
        }

        updateLocale(context, locale);
        final Context appContext = context.getApplicationContext();
        if (context != appContext) {
            updateLocale(appContext, locale);
        }
    }

    private void updateLocale(@NonNull Context context, @NonNull Locale locale) {
        final Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        config.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLayoutDirection(config.locale);
        }
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }





    /*public void bottomNavigationClickFragments(FragmentManager fragmentManager, int tabId, Context context) {
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
            fragmentManager.popBackStack();
        }

        if (tabId == R.id.tab_home) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.flRestaurantContainer, new VendorListFragment(), "home")
                    .addToBackStack(null)
                    .commit();
        } else if (tabId == R.id.tab_offer) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.flRestaurantContainer, new OffersFragment(), "offer")
                    .addToBackStack(null)
                    .commit();
        } else if (tabId == R.id.tab_cart) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.flRestaurantContainer, new CartFragment(), "explore")
                    .addToBackStack(null)
                    .commit();

        } else if (tabId == R.id.tab_profile) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.flRestaurantContainer, new ProfileFragment(), "profile")
                    .addToBackStack(null)
                    .commit();
           // CommonFunctions.getInstance().newIntent(context, ProfileActivity.class, Bundle.EMPTY, false, false);

        }
    }*/

    // --- TYPE_ERROR
    public void validationEmptyError(Context context, String message) {
        View parent = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar snackbar;
        snackbar = Snackbar.make(parent, message, Snackbar.LENGTH_SHORT);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(context.getResources().getColor(R.color.red));
        TextView textView = (TextView) snackBarView.findViewById(R.id.snackbar_text);
        textView.setTextColor(context.getResources().getColor(R.color.colorWhite));
        //snackbar.show();

        MDToast mdToast = MDToast.makeText(context, message, Toast.LENGTH_SHORT, MDToast.TYPE_ERROR);
        mdToast.show();
    }

    // --- TYPE_ERROR
    public void validationError(Context context, String message) {
        View parent = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar snackbar;
        snackbar = Snackbar.make(parent, message, Snackbar.LENGTH_SHORT);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(context.getResources().getColor(R.color.red));
        TextView textView = (TextView) snackBarView.findViewById(R.id.snackbar_text);
        textView.setTextColor(context.getResources().getColor(R.color.colorWhite));
        //snackbar.show();

        MDToast mdToast = MDToast.makeText(context, message, Toast.LENGTH_SHORT, MDToast.TYPE_ERROR);
        mdToast.show();
    }

    // --- TYPE_SUCCESS
    public void successResponseToast(Context context, String message) {
        View parent = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar snackbar;
        snackbar = Snackbar.make(parent, message, Snackbar.LENGTH_SHORT);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(context.getResources().getColor(R.color.green));
        TextView textView = (TextView) snackBarView.findViewById(R.id.snackbar_text);
        textView.setTextColor(context.getResources().getColor(R.color.colorWhite));
        //snackbar.show();

        MDToast mdToast = MDToast.makeText(context, message, Toast.LENGTH_SHORT, MDToast.TYPE_SUCCESS);
        mdToast.show();
    }


    public void validationError_Custom(Context context, String message) {
        Toast toast = Toast.makeText(context, "  "+message+"  ", Toast.LENGTH_LONG);
        View toastView = toast.getView();

        TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
        toastMessage.setTextSize(18);
        toastMessage.setTextColor(Color.WHITE);
        // toastMessage.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_fly, 0, 0, 0);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toastMessage.setGravity(Gravity.CENTER);
        toastMessage.setCompoundDrawablePadding(16);
        toastView.setBackgroundColor(Color.RED);
        toast.show();

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(50); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        toastMessage.startAnimation(anim);




       /* LayoutInflater inflater = context.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast,
                (ViewGroup) context.findViewById(R.id.toast_layout_root));

        TextView text = layout.findViewById(R.id.text);
        text.setText(message);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();*/

    }

    public String GeoCodingAddress_details(Activity activity, double workLatitude, double workLongitude) {

        List<Address> addresses = null;


        Geocoder mGeocoder = new Geocoder(activity, Locale.getDefault());

        try {
            addresses = mGeocoder.getFromLocation(workLatitude, workLongitude,
                    // In this sample, get just a single address.
                    1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String mPlaceName = "", mSubPlace = "", mLocality = "", mState = "", mCountry = "";
        try {
            if (addresses != null && addresses.size() != 0) {
                for (int i = 0; i < addresses.size(); i++) {
                    mPlaceName = addresses.get(i).getFeatureName();
                    mSubPlace = addresses.get(i).getSubLocality();
                    mLocality = addresses.get(i).getLocality();
                    mState = addresses.get(i).getAdminArea();
                    mCountry = addresses.get(i).getCountryName();
                }
            }


        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        String addressFull = "";
        if (!TextUtils.isEmpty(mPlaceName)) {
            addressFull = addressFull + mPlaceName;
        }
        if (!TextUtils.isEmpty(mSubPlace)) {
            addressFull = addressFull + "," + mSubPlace;
        }
        if (!TextUtils.isEmpty(mLocality)) {
            addressFull = addressFull + "," + mLocality;
        }
        if (!TextUtils.isEmpty(mState)) {
            addressFull = addressFull + "," + mState;
        }
        if (!TextUtils.isEmpty(mCountry)) {
            addressFull = addressFull + "," + mCountry;
        }

        return addressFull;
    }


    public Address geoCodingAddress(Activity activity, double workLatitude, double workLongitude) {

        List<Address> addresses = null;


        Geocoder mGeocoder = new Geocoder(activity, Locale.getDefault());

        try {
            addresses = mGeocoder.getFromLocation(workLatitude, workLongitude,
                    // In this sample, get just a single address.
                    1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Address address = null;
        try {
            if (addresses != null && addresses.size() != 0) {
                for (int i = 0; i < addresses.size(); i++) {
                    address = addresses.get(i);
                }
            }


        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }


        return address;
    }

    public boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public boolean isValidPassword(String password) {
        /*String passwordCombination = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        return password.matches(passwordCombination);*/
        return true;
    }

    public void loadImageByFresco(SimpleDraweeView sdView, String url) {
        try {
            url = url.startsWith("http") ? url : Urls.BASE_URL + url;
            Uri uri = Uri.parse(url);

            int width = 200, height = 200;
            //sdView.getHierarchy().setPlaceholderImage(R.drawable.ic_no_image);
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .disableDiskCache()
                    .setRequestPriority(Priority.HIGH)
                    .setResizeOptions(new ResizeOptions(width, height))
                    .build();
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setOldController(sdView.getController())
                    .setImageRequest(request)
                    .build();
            sdView.setController(controller);
             sdView.getHierarchy().setProgressBarImage(new CircleProgressDrawable());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadImageByFrescodrawble(SimpleDraweeView sdView, Integer resId) {
        try {
            Uri uri = new Uri.Builder()
                    .scheme(UriUtil.LOCAL_RESOURCE_SCHEME) // "res"
                    .path(String.valueOf(resId))
                    .build();
            sdView.setImageURI(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAmount(Double _total) {
        String total = _total.toString();
        if (SessionManager.getInstance().getFromPreference(SharedPrefConstants.LANGUAGE_CODE).isEmpty()
                || SessionManager.getInstance().getFromPreference(SharedPrefConstants.LANGUAGE_CODE).equals("en")) {
            return SessionManager.getInstance().getFromPreference(SharedPrefConstants.CURRENCY_CODE) + " " + total;
        } else {
            return total + " " + SessionManager.getInstance().getFromPreference(SharedPrefConstants.CURRENCY_CODE);
        }
    }


    public String getAmount(String orderTotal) {
        if (SessionManager.getInstance().getFromPreference(SharedPrefConstants.LANGUAGE_CODE).isEmpty()
                || SessionManager.getInstance().getFromPreference(SharedPrefConstants.LANGUAGE_CODE).equals("en")) {
            return SessionManager.getInstance().getFromPreference(SharedPrefConstants.CURRENCY_CODE) + " " + orderTotal;

        } else {
            return orderTotal + " " + SessionManager.getInstance().getFromPreference(SharedPrefConstants.CURRENCY_CODE);
        }
    }

    public void ChangeDirection(Activity mActivity, Toolbar toolbar) {
        if (SessionManager.getInstance().getFromPreference(SharedPrefConstants.LANGUAGE_CODE).equals(ConstantsInternal.ARABIC)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                mActivity.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                mActivity.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            }
        }
//        if (toolbar != null)
//            ChangeToolbarLanguage(mActivity, toolbar);
    }

    public void ChangeDirection(Activity mActivity) {
        if (SessionManager.getInstance().getFromPreference(SharedPrefConstants.LANGUAGE_CODE).equals(ConstantsInternal.ARABIC)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                mActivity.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                mActivity.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            }
        }
    }

    public void ChangeDirectionDialog(Dialog mDialog) {
        if (SessionManager.getInstance().getFromPreference(SharedPrefConstants.LANGUAGE_CODE).equals(ConstantsInternal.ARABIC)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                mDialog.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                mDialog.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            }
        }
    }

    public String roundOffDecimalValue(Double price, Boolean isLeft) {
        if (isLeft) {
            return CURRENCY_CODE + " " + new DecimalFormat("0.00").format(price);
        } else {
            return (new DecimalFormat("0.00").format(price) + " " + CURRENCY_CODE);
        }
    }


    /*public void ChangeToolbarLanguage(Activity mActivity, Toolbar mToolbar) {
        final int childCount = getAllViews(mToolbar).size();
        for (int i = 0; i < childCount; i++) {
            View view = getAllViews(mToolbar).get(i);
            if (view instanceof ImageView) {
                ImageView imageView = (ImageView) view;
                if (imageView.getId() == R.id.tlbar_back) {
                    if (SessionManager.AppProperties.getInstance().getAppDirection() == ConstantsInternal.AppDirection.RTL) {
                        imageView.setScaleX(-1);
                    } else {
                        imageView.setScaleX(1);
                    }
                }
            }
        }

    }*/
    private List<View> getAllViews(View v) {
        if (!(v instanceof ViewGroup) || ((ViewGroup) v).getChildCount() == 0) // It's a leaf
        {
            List<View> r = new ArrayList<View>();
            r.add(v);
            return r;
        } else {
            List<View> list = new ArrayList<View>();
            list.add(v); // If it's an internal node add itself
            int children = ((ViewGroup) v).getChildCount();
            for (int i = 0; i < children; ++i) {
                list.addAll(getAllViews(((ViewGroup) v).getChildAt(i)));
            }
            return list;
        }
    }


    /**
     * Date formation
     *
     * @param date
     * @param initDateFormat
     * @param endDateFormat
     * @return
     */
    public static String formatDate(String date, String initDateFormat, String endDateFormat) {
        Date initDate = null;
        String parsedDate = "";
        try {
            initDate = new SimpleDateFormat(initDateFormat).parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
            parsedDate = formatter.format(initDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }


    public static String CartTotal(Context context){
        String Total="0";
        RealmResults<CartRealmModel> itemList = RealmLibrary.getInstance().getCartList();

        Double totalPrice = 0.0;

            for (int count = 0; count < itemList.size(); count++) {
                CartRealmModel item = itemList.get(count);
                Double itemPrice = Double.parseDouble(item.getPrice());
                Double ingPrice = 0.0;
                for (int count1 = 0; count1 < item.getIngredientsRealmModel().size(); count1++) {
                    ingPrice = ingPrice + Double.parseDouble(item.getIngredientsRealmModel().get(count1).getPrice());
                }

                totalPrice = totalPrice + ((itemPrice + ingPrice) * Integer.parseInt(item.getQTY()));

            }

        Total = String.valueOf(totalPrice);

        return Total;
    }


    public static void loadImage(Context mContext, ImageView sdView, String url) {
        {
            if (!url.isEmpty()) {
                Picasso
                        .get()
                        .load(url)
                        .resize(600, 400) // resizes the image to these dimensions (in pixel)
                        .centerInside()
                        .into(sdView, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError(Exception e) {

                            }

                        });
            }
        }
    }

    public static void showCustomSuccessToast(Activity context, String msg){
        LayoutInflater inflater = context.getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast_view, (ViewGroup) context.findViewById(R.id.custom_toast_container));
        TextView tv = (TextView) layout.findViewById(R.id.txtvw);
        tv.setText(msg);
        Toast toast = new Toast(context.getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }


    public static void showCustomErrorToast(Activity context, String msg){
        LayoutInflater inflater = context.getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast_error_view, (ViewGroup) context.findViewById(R.id.custom_toast_container));
        TextView tv = (TextView) layout.findViewById(R.id.txtvw);
        tv.setText(msg);
        Toast toast = new Toast(context.getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

}
