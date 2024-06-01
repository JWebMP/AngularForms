package com.jwebmp.angular.forms;

import com.guicedee.client.IGuiceContext;
import com.jwebmp.core.base.angular.client.annotations.boot.NgBootModuleImport;
import com.jwebmp.core.base.angular.client.annotations.components.NgInput;
import com.jwebmp.core.base.angular.client.annotations.constructors.NgConstructorParameter;
import com.jwebmp.core.base.angular.client.annotations.references.NgComponentReference;
import com.jwebmp.core.base.angular.client.annotations.references.NgImportReference;
import com.jwebmp.core.base.angular.client.annotations.structures.NgField;
import com.jwebmp.core.base.angular.client.services.AnnotationHelper;
import com.jwebmp.core.base.angular.client.services.interfaces.IComponent;
import com.jwebmp.core.base.angular.client.services.interfaces.INgComponent;
import com.jwebmp.core.base.angular.client.services.interfaces.INgServiceProvider;
import com.jwebmp.core.base.html.Form;

import java.util.List;

import static com.jwebmp.core.base.angular.client.services.interfaces.AnnotationUtils.getNgComponentReference;

@NgBootModuleImport("FormsModule")
@NgComponentReference(FormRegexProvider.class)
@NgImportReference(value = "FormBuilder, FormGroup, Validators, FormControl, FormArray, ControlValueAccessor ",
                   reference = "@angular/forms")
@NgConstructorParameter("private formBuilder : FormBuilder")
@NgField("regex = FormRegexProvider;")

public class AngularForm<J extends AngularForm<J>> extends Form<J> implements INgComponent<J>
{
    private INgServiceProvider<?> formDataProvider;

    AngularForm()
    {

    }

    @Override
    public List<String> afterViewInit()
    {
        List<String> out = INgComponent.super.afterViewInit();
        return out;
    }

    public AngularForm(String id, INgServiceProvider<?> formDataProvider)
    {
        setID(id);
        this.formDataProvider = formDataProvider;
    }

    @Override
    public void init()
    {
        addAttribute("#" + getID(), "ngForm");
        super.init();
    }

    @Override
    public List<String> componentMethods()
    {
        List<String> out = INgComponent.super.componentMethods();
        if (formDataProvider == null)
        {
            return out;
        }

        StringBuilder sendDataString = new StringBuilder();

        sendDataString.append("onSubmit() {\n");

        List<NgInput> inputList = IGuiceContext.get(AnnotationHelper.class)
                                               .getAnnotationFromClass(getClass(), NgInput.class);
        StringBuilder inputs = new StringBuilder();
        for (NgInput ngInput : inputList)
        {
            if (ngInput.additionalData())
            {
                inputs.append("\t\tthis." + getFormDataProvider().getAnnotation()
                                                                 .referenceName() +
                                      ".additionalData." + ngInput.value() + " = this." + ngInput.value() + ";\n");
            }
        }
        sendDataString.append(inputs.toString());


        sendDataString.append("" +
                                      "" +
                                      "" +
                                      "" + " this." + getFormDataProvider().getAnnotation()
                                                                           .referenceName() +
                                      ".sendData(this." + getFormDataProvider().getAnnotation()
                                                                               .referenceName() + "." + getFormDataProvider().getAnnotation()
                                                                                                                             .variableName() + ");  \n" + "}\n");
        out.add(sendDataString.toString());

        return out;
    }

    public INgServiceProvider<?> getFormDataProvider()
    {
        return formDataProvider;
    }

    public String getServiceName()
    {
        if (formDataProvider == null)
        {
            return "formDataProvider";
        }
        return getFormDataProvider().getAnnotation()
                                    .referenceName();
    }

    @Override
    public List<NgComponentReference> getComponentReferences()
    {
        List<NgComponentReference> out = INgComponent.super.getComponentReferences();
        out.add(getNgComponentReference((Class<? extends IComponent<?>>) formDataProvider.getClass()));
        return out;
    }

}
