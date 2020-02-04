package com.navin.downloadfiletest.ui.fragment;

import android.content.SharedPreferences;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.navin.downloadfiletest.data.remote.NetworkService;
import com.navin.downloadfiletest.data.remote.Networking;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import kotlin.jvm.JvmField;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestCityQueryViewModel {

    @Rule
    @JvmField
    public TestRule rule = new InstantTaskExecutorRule();
    @Mock
    @JvmField
    public NetworkService networkService = null;
    @Mock
    @JvmField
    public SharedPreferences sharedPreferences = null;
    public CompositeDisposable compositeDisposable = null;

    private CityQueryViewModel cityQueryViewModel = null;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        compositeDisposable = new CompositeDisposable();
        cityQueryViewModel =
                new CityQueryViewModel(compositeDisposable, networkService, sharedPreferences);
    }

    @Test
    public void _01TestNonNull() {
        assert (cityQueryViewModel != null);
        assert (networkService != null);
        assert (sharedPreferences != null);
    }

    @Test
    public void _02_TestUpdateLocallySavedList() throws InterruptedException {
        //Mocking
        when(sharedPreferences.getString("Constants.LOCAL_LIST", ""))
                .thenReturn("{\"CardList\": [{\"cityName\": \"Thana-India \",\"timeStamp\":\"1580810233667\"}]}");
        ArgumentCaptor<List<String>> captor = ArgumentCaptor.forClass(List.class);
        Observer observer = mock(Observer.class);
        cityQueryViewModel.getGetLocalSavedData().observeForever(observer);

        //Action
        cityQueryViewModel.updateLocallySavedList();

        Thread.sleep(500);

        //Verify
        verify(observer).onChanged(captor.capture());
        assert (captor.getValue().size() == 1);
        assert (captor.getValue().contains("Thana-India "));
    }

    @Test
    public void _03_TestUpdateLocallySavedEmptyList() throws InterruptedException {
        //Mocking
        when(sharedPreferences.getString("Constants.LOCAL_LIST", ""))
                .thenReturn("");
        ArgumentCaptor<List<String>> captor = ArgumentCaptor.forClass(List.class);
        Observer observer = mock(Observer.class);
        cityQueryViewModel.getGetLocalSavedData().observeForever(observer);

        //Action
        cityQueryViewModel.updateLocallySavedList();

        Thread.sleep(500);

        //Verify
        verify(observer).onChanged(captor.capture());
        assert (captor.getValue().size() == 0);
    }

    @Test
    public void _04_TestDoSearchCity() throws InterruptedException {
        //Mocking
        Networking.API_VAL = "DUMMY_TEXT";
        final Single results = mock(Single.class);
        ArgumentCaptor captor = mock(ArgumentCaptor.class);
        Observer observer = mock(Observer.class);

        cityQueryViewModel.getGetSearchResults().observeForever(observer);

        when(networkService.doSearchCity(Networking.API_VAL, "TEST", 4, ""))
                .thenReturn(results);

        when(results.subscribeOn(Schedulers.io())).thenReturn(
                results
        );

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                results.doOnError(null);
                return null;
            }
        }).when(results).subscribe();

        //Action
        cityQueryViewModel.getSearchResult("TEST");

        Thread.sleep(500);

        //Verify
        verify(observer).onChanged(captor.capture());
        assert (captor.getValue() == null);
    }


}