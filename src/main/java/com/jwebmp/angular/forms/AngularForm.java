package com.jwebmp.angular.forms;

import com.guicedee.client.IGuiceContext;
import com.jwebmp.core.base.angular.client.annotations.components.NgInput;
import com.jwebmp.core.base.angular.client.annotations.references.NgComponentReference;
import com.jwebmp.core.base.angular.client.annotations.references.NgImportReference;
import com.jwebmp.core.base.angular.client.annotations.structures.NgField;
import com.jwebmp.core.base.angular.client.services.AnnotationHelper;
import com.jwebmp.core.base.angular.client.services.interfaces.IComponent;
import com.jwebmp.core.base.angular.client.services.interfaces.INgComponent;
import com.jwebmp.core.base.angular.client.services.interfaces.INgServiceProvider;
import com.jwebmp.core.base.html.Form;

import java.util.List;
import java.util.Set;

import static com.jwebmp.core.base.angular.client.services.interfaces.AnnotationUtils.getNgComponentReference;

@NgComponentReference(FormRegexProvider.class)
@NgField("regex = FormRegexProvider;")
@NgImportReference(value = "FormsModule", reference = "@angular/forms")

public class AngularForm<J extends AngularForm<J>> extends Form<J> implements INgComponent<J>
{
    private INgServiceProvider<?> formDataProvider;

    AngularForm()
    {

    }

    @Override
    public Set<String> moduleImports()
    {
        var s = INgComponent.super.moduleImports();
        s.add("FormsModule");
        return s;
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
    protected void init()
    {
        if (this.formDataProvider != null)
        {
            addConfiguration(getNgComponentReference((Class<? extends IComponent<?>>) formDataProvider.getClass()));
        }
        addAttribute("#" + getID(), "ngForm");
        super.init();
    }

    @Override
    public List<String> methods()
    {
        List<String> out = INgComponent.super.methods();
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
