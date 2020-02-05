package com.navin.downloadfiletest.ui.fragment;


import android.content.SharedPreferences;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.navin.downloadfiletest.data.remote.NetworkService;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.disposables.CompositeDisposable;
import kotlin.jvm.JvmField;

import static android.content.SharedPreferences.Editor;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestCityDetailsViewModel {

    @Rule
    @JvmField
    public TestRule rule = new InstantTaskExecutorRule();
    @Mock
    @JvmField
    public NetworkService networkService = null;
    @Mock
    @JvmField
    public SharedPreferences sharedPreferences = null;
    private CompositeDisposable compositeDisposable = null;

    private CityDetailsViewModel cityDetailsViewModel = null;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        compositeDisposable = new CompositeDisposable();
        cityDetailsViewModel =
                new CityDetailsViewModel(compositeDisposable, networkService, sharedPreferences);
    }

    @Test
    /*
      Test to check if created items are null or not
     */
    public void _01TestNonNull() {
        assert (cityDetailsViewModel != null);
        assert (networkService != null);
        assert (sharedPreferences != null);
    }

    @Test
    /*
      Test to check if shared preference don't contain any item and we want to save a city
     */
    public void _02TestSaveToLocalFirstItem() {
        //Mocking
        Editor editor = Mockito.mock(Editor.class);
        when(sharedPreferences.getString("Constants.LOCAL_LIST", "")).thenReturn("");

        when(sharedPreferences.edit()).thenReturn(editor);

        when(editor.putString(anyString(), anyString())).thenReturn(editor);
        doNothing().when(editor).apply();

        //Action
        cityDetailsViewModel.saveToLocal("CITY");

        //Verify
        verify(editor, times(1)).putString(anyString(), anyString());
        verify(editor, times(1)).apply();
    }


    @Test
    /*
      Test to check if shared preference contains multiple item and we want to save a city
     */
    public void _03TestSaveToLocalMultipleItem() {
        //Mocking
        Editor editor = Mockito.mock(Editor.class);
        when(sharedPreferences.getString("Constants.LOCAL_LIST", "")).thenReturn("{\"CityData\":[{\"cityName\": \"Chana-Chile \", \"timeStamp\":\"1580823226010\"}]}");

        when(sharedPreferences.edit()).thenReturn(editor);

        when(editor.putString(anyString(), anyString())).thenReturn(editor);
        doNothing().when(editor).apply();

        //Action
        cityDetailsViewModel.saveToLocal("CITY");

        //Verify
        verify(editor, times(1)).putString(anyString(), anyString());
        verify(editor, times(1)).apply();
    }


    @Test
    /*
      Test to check if we pass empty city will it be saved in local preference
     */
    public void _04TestSaveToLocalNoItem() {
        //Mocking
        Editor editor = Mockito.mock(Editor.class);
        when(sharedPreferences.getString("Constants.LOCAL_LIST", "")).thenReturn("");

        when(sharedPreferences.edit()).thenReturn(editor);

        when(editor.putString(anyString(), anyString())).thenReturn(editor);
        doNothing().when(editor).apply();

        //Action
        cityDetailsViewModel.saveToLocal("");

        //Verify
        verify(editor, times(0)).putString(anyString(), anyString());
        verify(editor, times(0)).apply();
    }

    @After
    public void tearDown() {
        compositeDisposable = null;
        cityDetailsViewModel = null;
        networkService = null;
        sharedPreferences = null;
    }
}
