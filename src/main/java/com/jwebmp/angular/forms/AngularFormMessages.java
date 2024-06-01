package com.jwebmp.angular.forms;

import com.jwebmp.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.html.Input;

public class AngularFormMessages<J extends AngularFormMessages<J>> extends DivSimple<J>
{
    private Input<?, ?> input;

    public AngularFormMessages(Input<?, ?> input)
    {
        this.input = input;
    }

    public J addMessage(InputErrorValidations validations, String messageString, boolean inline)
    {
        return addMessage(validations, messageString);
    }

    public J addMessage(InputErrorValidations validations, String messageString)
    {
        addAttribute("*ngIf", "" + input.getName() + ".touched && " + "" + input.getName() + ".invalid");
        AngularFormMessage<?> message = new AngularFormMessage<>(validations, messageString, input.getName());
        message.setError(validations);
        message.setErrorMessage(messageString);
        add(message);
        return (J) this;
    }

    public Input<?, ?> getInput()
    {
        return input;
    }

    public AngularFormMessages<J> setInput(Input<?, ?> input)
    {
        this.input = input;
        return this;
    }
}
