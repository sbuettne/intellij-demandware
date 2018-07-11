package com.demandware.studio.facet;

import com.demandware.studio.SFCCIcons;
import com.demandware.studio.projectWizard.SFCCModuleType;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportConfigurable;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModel;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportProvider;
import com.intellij.openapi.module.ModuleType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class DWFrameworkSupportProvider extends FrameworkSupportProvider {

    protected DWFrameworkSupportProvider() {
        super("SFCC", DWFacetType.INSTANCE.getPresentableName());
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return SFCCIcons.SFCC_ICON;
    }

    @NotNull
    @Override
    public FrameworkSupportConfigurable createConfigurable(@NotNull FrameworkSupportModel model) {
        return new DWFrameworkSupportConfigurable(model);
    }

    @Override
    public boolean isEnabledForModuleType(@NotNull ModuleType moduleType) {
        return moduleType instanceof SFCCModuleType;
    }
}
