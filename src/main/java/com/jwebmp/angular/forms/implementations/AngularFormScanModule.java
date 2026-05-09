package com.jwebmp.angular.forms.implementations;

import com.guicedee.client.services.config.IGuiceScanModuleInclusions;

import java.util.Set;

public class AngularFormScanModule implements IGuiceScanModuleInclusions<AngularFormScanModule>
{
    @Override
    public Set<String> includeModules()
    {
        return Set.of("com.jwebmp.angular.forms");
    }
}
