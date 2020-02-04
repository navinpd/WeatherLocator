package com.navin.downloadfiletest.ui.fragment;


import android.content.SharedPreferences;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.navin.downloadfiletest.data.remote.NetworkService;

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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
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
    public CompositeDisposable compositeDisposable = null;

    private CityDetailsViewModel cityDetailsViewModel = null;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        compositeDisposable = new CompositeDisposable();
        cityDetailsViewModel =
                new CityDetailsViewModel(compositeDisposable, networkService, sharedPreferences);
    }

    @Test
    public void _01TestNonNull() {
        assert (cityDetailsViewModel != null);
        assert (networkService != null);
        assert (sharedPreferences != null);
    }

    @Test
    public void _02TestSaveToLocalFirstItem() {
        Editor editor = Mockito.mock(Editor.class);
        when(sharedPreferences.getString("Constants.LOCAL_LIST", "")).thenReturn("");

        when(sharedPreferences.edit()).thenReturn(editor);

        when(editor.putString(anyString(), anyString())).thenReturn(editor);
        doNothing().when(editor).apply();

        //Action
        cityDetailsViewModel.saveToLocal("CITY");

        verify(editor).apply();
        verify(editor).putString(anyString(), anyString());
    }


}
