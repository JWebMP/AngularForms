
package com.jwebmp.angular.forms.enumerations;

/**
 * Valid $error identification fields
 */
public enum InputErrorValidations
{
    required,
    email,
    max,
    maxLength,
    min,
    minLength,
    number,
    pattern,
    url,
    date,
    datetimelocal,
    time,
    week,
    month;

    public String toFullString()
    {
        return "$error." + name().toLowerCase() + "";
    }

    @Override
    public String toString()
    {
        return name().toLowerCase();
    }
}
