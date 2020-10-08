<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="xml" version="1.0" encoding="utf-8" indent="yes" />
<xsl:template match="/*">
    <Cat_AjusteGC>
        <Ajuste_P_General>
            <xsl:for-each select="rows">
                <xsl:for-each select="RAZONSOCIAL">
                    <RazonSocial>
                        <xsl:value-of select="." />
                    </RazonSocial>
                </xsl:for-each>
            </xsl:for-each>
        </Ajuste_P_General>
        <Ajuste_P_General>
        <xsl:for-each select="rows">
            <xsl:for-each select="CLASIFICACION_CLIENTE2">
                <Segmento>
                    <xsl:value-of select="." />
                </Segmento>
            </xsl:for-each>
        </xsl:for-each>
        </Ajuste_P_General>
    </Cat_AjusteGC>
</xsl:template>
</xsl:stylesheet>




        <?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="xml" version="1.0" encoding="utf-8" indent="yes" />
<xsl:template match="/*">


    <GestionGlobalFibercorp>
        <M_SA_SolicitudAprov>
            <kmInfodelAprov>
                <kmInfoCliente>
                    <xsl:for-each select="subscriber">
                        <xsl:for-each select="id">
                            <sIdClienteOpen>
                                <xsl:value-of select="." />
                            </sIdClienteOpen>
                        </xsl:for-each>
                    </xsl:for-each>
                </kmInfoCliente>
            </kmInfodelAprov>
            <kmVariablesAux>
                <xsl:for-each select="result">
                    <xsl:for-each select="code">
                        <sCodigoRespuesta>
                            <xsl:value-of select="." />
                        </sCodigoRespuesta>
                    </xsl:for-each>
                </xsl:for-each>
                <xsl:for-each select="result">
                    <xsl:for-each select="message">
                        <sMensajeRespuesta>
                            <xsl:value-of select="." />
                        </sMensajeRespuesta>
                    </xsl:for-each>
                </xsl:for-each>
                <sTareaParaRetornar>
                    <xsl:value-of select="'SA_OPEN_BC'" />
                </sTareaParaRetornar>
            </kmVariablesAux>
        </M_SA_SolicitudAprov>
    </GestionGlobalFibercorp>
</xsl:template>
</xsl:stylesheet>-
