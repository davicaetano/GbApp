package com.davicaetano.gbapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.davicaetano.gbapp.gbApi.GbApi;
import com.davicaetano.gbapp.gbApi.GbApiModel;
import com.davicaetano.gbapp.gbApi.GbCallback;
import com.davicaetano.gbapp.gbApi.GbRetrofit;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.concurrent.CountDownLatch;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(RobolectricTestRunner.class)
@Config(
        manifest="app/src/main/AndroidManifest.xml",
        sdk = 23)
public class GbApiCityStateNotNullTest {

    private GbApi gbApi;
    private MockWebServer mockWebServer;
    @Before
    public void setUp() throws Exception{
        Context context = RuntimeEnvironment.application;
        mockWebServer = new MockWebServer();

        InputStream in = context.getAssets().open("GbApiCityStateNotNullTest.json");
        StringWriter writer = new StringWriter();
        IOUtils.copy(in, writer, "UTF8");
        String json = writer.toString();

        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
                .setBody(json));

        mockWebServer.start();
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);


        GbRetrofit gbRetrofit = new GbRetrofit(){
            @Override
            public String getGbUrl() {
                return mockWebServer.url("/").toString();
            }
        };
        gbApi = new GbApi(gbRetrofit);

    }

    @Test
    public void testGbApiCityStateNotNullTest(){
        final CountDownLatch latch = new CountDownLatch(1);
        gbApi.callGbApi(new GbCallback<GbApiModel>() {
            @Override
            public void onSuccess(GbApiModel answer) {
                latch.countDown();
                assertEquals("10", answer.getTotal());
                assertEquals("San Francisco", answer.getData().get(0).getGbVenue().getCity());
                assertEquals("CA", answer.getData().get(0).getGbVenue().getState());
                assertNull(answer.getData().get(1).getGbVenue().getCity());
            }

            @Override
            public void onFailure(Throwable t) {
                Assert.fail();
            }
        }, false);

        assertEquals(latch.getCount(), 0);
    }
}