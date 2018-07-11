package com.demandware.studio.facet;

import com.demandware.studio.SFCCIcons;
import com.demandware.studio.projectWizard.SFCCModuleType;
import com.intellij.facet.Facet;
import com.intellij.facet.FacetType;
import com.intellij.facet.FacetTypeId;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class DWFacetType extends FacetType<DWFacet, DWFacetConfiguration> {
    public static final String STRING_ID = "sfcc";
    public static final String PRESENTABLE_NAME = "SFCC";
    public static final FacetTypeId<DWFacet> ID = new FacetTypeId<DWFacet>(STRING_ID);
    public static final DWFacetType INSTANCE = new DWFacetType();

    public DWFacetType() {
        super(ID, STRING_ID, PRESENTABLE_NAME);
    }

    @Override
    public DWFacetConfiguration createDefaultConfiguration() {
        return new DWFacetConfiguration();
    }

    @Override
    public DWFacet createFacet(@NotNull Module module, String name, @NotNull DWFacetConfiguration configuration, Facet underlyingFacet) {
        return new DWFacet(this, module, name, configuration, underlyingFacet);
    }

    @Override
    public boolean isSuitableModuleType(ModuleType moduleType) {
        return moduleType instanceof SFCCModuleType;
    }

    @Override
    public boolean isOnlyOneFacetAllowed() {
        return true;
    }

    @Override
    public Icon getIcon() {
        return SFCCIcons.SFCC_ICON;
    }
}
