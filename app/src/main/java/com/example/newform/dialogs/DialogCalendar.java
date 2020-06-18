package com.example.newform.dialogs;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.newform.interfaces.GenericCode;
import com.example.newform.utils.UtilDate;

import java.util.Calendar;
import java.util.Date;

public class DialogCalendar extends DatePickerDialog {

    private Context context;
    private Calendar cal = Calendar.getInstance();
    private GenericCode.DateCode dateCode;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public DialogCalendar(@NonNull Context context, Date date, GenericCode.DateCode dateCode) {
        super(context, null, UtilDate.getYearFromDate(date), UtilDate.getMonthFromDate(date), UtilDate.getDayFromDate(date));
        this.context = context;
        this.dateCode = dateCode;
        this.cal.setTime(date);
    }

    @Override
    public void onDateChanged(@NonNull DatePicker view, int year, int month, int dayOfMonth) {
        Calendar newDate = Calendar.getInstance();
        newDate.set(Calendar.YEAR, year);
        newDate.set(Calendar.MONTH, month);
        newDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        dateCode.code(newDate.getTime());
    }

}
