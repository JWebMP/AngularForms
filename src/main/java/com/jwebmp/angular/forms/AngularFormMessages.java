package com.jwebmp.angular.forms;

import com.guicedee.guicedinjection.pairing.Pair;
import com.jwebmp.angular.forms.enumerations.InputErrorValidations;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.html.Input;

import java.util.ArrayList;
import java.util.List;

public class AngularFormMessages<J extends AngularFormMessages<J>> extends DivSimple<J>
{
    private Input<?, ?> input;
    private List<Pair<InputErrorValidations, String>> formMessages = new ArrayList<>();

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
        formMessages.add(Pair.of(validations, messageString));

        return (J) this;
    }

    @Override
    protected void init()
    {
        if (!isInitialized())
        {
            addAttribute("*ngIf", input.getName() + ".touched && " + input.getName() + ".invalid");
            for (Pair<InputErrorValidations, String> formMessage : formMessages)
            {
                AngularFormMessage<?> message = new AngularFormMessage<>(formMessage.getKey(), formMessage.getValue(), input.getName());
                message.setError(formMessage.getKey());
                message.setErrorMessage(formMessage.getValue());
                add(message);
            }
        }
        super.init();
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
