package info.yash.cricket;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;



public class MainActivity extends Activity {

	// set your custom url here
	String url = "https://www.google.co.in/search?ei=nk3pW7nxJ8im9QPUwrLwBA&q=live+cricket+score&oq=live+cricket+&gs_l=psy-ab.1.0.0i203k1l9j0i2i159i203k1.38065.42266.0.43817.11.11.0.0.0.0.293.1788.0j8j3.11.0....0...1c.1.64.psy-ab..0.11.1786...0j0i10i67k1j0i67k1j0i10k1j35i39k1j0i2k1.0.WeWuQwRMvYE#sie=lg;/g/11ggr_x7ct;5;/m/021q23;mt;fp;1";

	// if you want to show progress bar on splash screen
	//Boolean showProgressOnSplashScreen = true;

	WebView mWebView;
	ProgressBar prgs;
	RelativeLayout splash;

	@SuppressWarnings("deprecation")
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_PROGRESS,
				Window.PROGRESS_VISIBILITY_ON);

		mWebView = (WebView) findViewById(R.id.wv);
		//mWebView.getSettings().setJavaScriptEnabled(true);
//
//		mWebView.setWebViewClient(new WebViewClient()
//
//		{
//			@Override
//			public void onPageFinished(WebView view, String url)
//			{
//				mWebView.loadUrl("javascript:(function() { " +
//						"var head = document.getElementsByClassName('ouiajb')[0].style.display='none'; " +
//						"})()");
////				mWebView.loadUrl("javascript:(function() { " +
////						"document.document.getElementById('divHeader').style.display='none'('divHeader')[0].style.display='none'; " +
////						"})()");
//
//			}
//		});
		prgs = (ProgressBar) findViewById(R.id.progressBar);
		//main_layout = (RelativeLayout) findViewById(R.id.main_layout);

		// splash screen View
		/*if (!showProgressOnSplashScreen)
			((ProgressBar) findViewById(R.id.progressBarSplash)).setVisibility(View.GONE);

		splash = (RelativeLayout) findViewById(R.id.splash);*/

//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//
//			// get status bar height to push webview below that
//			int result = 0;
//			int resourceId = getResources().getIdentifier("status_bar_height",
//					"dimen", "android");
//			if (resourceId > 0) {
//				result = getResources().getDimensionPixelSize(resourceId);
//			}
//
//			// set top padding to status bar
//			main_layout.setPadding(0, result, 0, 0);
//		}
		
		mWebView.loadUrl(url);

		// control javaScript and add html5 features
		mWebView.setFocusable(true);
		mWebView.setFocusableInTouchMode(true);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setRenderPriority(RenderPriority.HIGH);
		mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		mWebView.getSettings().setDomStorageEnabled(true);
		mWebView.getSettings().setAppCacheEnabled(true);
		mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		mWebView.getSettings().setDatabaseEnabled(true);
		mWebView.getSettings().setDatabasePath(
				this.getFilesDir().getPath() + this.getPackageName()
						+ "/databases/");

		// this force use chromeWebClient
		mWebView.getSettings().setSupportMultipleWindows(true);

		mWebView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return false;
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				if (prgs.getVisibility() == View.GONE) {
					prgs.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void onLoadResource(WebView view, String url) {
				super.onLoadResource(view, url);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
//               mWebView.loadUrl("javascript:(function() { " +
//                       "document.getElementByClassName('ouiajb').style.display='none'; " +
//                        "})()");

                if (prgs.getVisibility() == View.VISIBLE)
					prgs.setVisibility(View.GONE);

				// check if splash is still there, get it away!
				//if (splash.getVisibility() == View.VISIBLE)
					//splash.setVisibility(View.GONE);
				// slideToBottom(splash);

			}

		});

	}



	@Override
	public boolean onKeyDown(final int keyCode, final KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
			mWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
