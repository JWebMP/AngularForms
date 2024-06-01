package com.jwebmp.angular.forms;

import com.jwebmp.core.base.angular.client.annotations.angular.NgDataType;
import com.jwebmp.core.base.angular.client.services.interfaces.INgDataType;
import com.jwebmp.core.utilities.regex.RegularExpressionsDTO;

import java.util.ArrayList;
import java.util.List;

@NgDataType
public class FormRegexProvider implements INgDataType<FormRegexProvider>
{
    @Override
    public List<String> componentFields()
    {
        List<String> out = new ArrayList<>();
        RegularExpressionsDTO registeredExpressions = new RegularExpressionsDTO();
        registeredExpressions.getRegularExpressions()
                             .forEach((name, pattern) -> {
                                 out.add("public static " + name + " : string = `" + pattern.toString()
                                                                                            .replace("`", "\\`") + "`");
                             });
        return out;
    }
}
