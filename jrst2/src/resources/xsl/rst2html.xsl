<?xml version="1.0" encoding="ISO-8859-1" standalone="yes"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns="http://www.w3.org/TR/xhtml1/strict">

	<xsl:output method="xml" encoding="ISO-8859-1" indent="yes"/>

	<xsl:template match="/document">	
	<html>
	  <head>
	    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-15" />
	    <meta name="generator" content="JRST http://jrst.labs.libre-entreprise.org/" />
	    <title><xsl:value-of select="title"/></title>
	  </head>
	  <body>	    
		<xsl:apply-templates/>
	  </body>
	</html>
	</xsl:template>

	<xsl:template match="title">
	   <xsl:element name="h{count(ancestor::section) + 1}">
         <xsl:apply-templates/>
       </xsl:element>
	</xsl:template>

	<xsl:template match="docinfo">
	  <div class="docinfo"><xsl:apply-templates/></div>
	</xsl:template>
	
	<xsl:template match="author|authors|date|version">
	  <div class="field {name(.)}"><span class="field_name"><xsl:value-of select="name(.)"/></span><span class="field_body"><xsl:apply-templates/></span></div>
	</xsl:template>
	
	<xsl:template match="transition">
	  <hr/>
	</xsl:template>

	<xsl:template match="section">
	  <xsl:apply-templates/>
	</xsl:template>

    <xsl:template match="list_item/paragraph[1] | definition_list_item/*/paragraph[1] | field/*/paragraph[1] | option/*/paragraph[1]">
            <!--XXX - Unclear how to handle multi-paragraph list items.
             | Certainly when they're single paragraphs, we don't want them
             | wrapped in a <P> tag.  This seems to work okay.
             +-->
            <xsl:apply-templates/>
    </xsl:template>

	<xsl:template match="paragraph">
	  <p><xsl:apply-templates/></p>
	</xsl:template>

	<xsl:template match="reference">
	  <a href="{@refuri}"><xsl:apply-templates/></a>
	</xsl:template>

	<xsl:template match="emphasis">
	  <em><xsl:apply-templates/></em>
	</xsl:template>
	
	<xsl:template match="strong">
	  <b><xsl:apply-templates/></b>
	</xsl:template>
	
	<xsl:template match="literal">
		<code><xsl:value-of select="text()"/></code>
	</xsl:template>
	
	<xsl:template match="literal_block">
		<pre><xsl:value-of select="text()"/></pre>
	</xsl:template>

	<xsl:template match="table">
		<pre><xsl:value-of select="text()"/></pre>
	</xsl:template>

	<xsl:template match="bullet_list">
		<ul><xsl:apply-templates/></ul>
	</xsl:template>

	<xsl:template match="enumerated_list">
		<ol>
		  	<xsl:choose>
			  	<xsl:when test="@enumtype='arabic'">
			  		<xsl:attribute name="type">1</xsl:attribute>
			    </xsl:when>
				<xsl:when test="@enumtype='loweralpha'">
					<xsl:attribute name="type">a</xsl:attribute>
				</xsl:when>
				<xsl:when test="@enumtype='upperalpha'">
					<xsl:attribute name="type">A</xsl:attribute>
				</xsl:when>
	            <xsl:when test="@enumtype='lowerroman'">
	                    <xsl:attribute name="type">i</xsl:attribute>
	            </xsl:when>
	            <xsl:when test="@enumtype='upperroman'">
	                    <xsl:attribute name="type">I</xsl:attribute>
	            </xsl:when>
            </xsl:choose>
	        <xsl:copy-of select="@start"/>
			<xsl:apply-templates/>
		</ol>
	</xsl:template>

	<xsl:template match="list_item">
		<li><xsl:apply-templates/></li>
	</xsl:template>

	<xsl:template match="field_list">
		<div class="field_list"><xsl:apply-templates/></div>
	</xsl:template>

	<xsl:template match="field">
		<div class="field"><xsl:apply-templates/></div>
	</xsl:template>

	<xsl:template match="field_name">
		<span class="field_name"><xsl:apply-templates/></span>
	</xsl:template>

	<xsl:template match="field_body">
		<span class="field_body"><xsl:apply-templates/></span>
	</xsl:template>

	<xsl:template match="definition_list">
		<dl class="definition_list"><xsl:apply-templates/></dl>
	</xsl:template>

	<xsl:template match="definition_list_item">
		<xsl:apply-templates/>
	</xsl:template>

	<xsl:template match="term">
		<dt class="term"><xsl:apply-templates/><xsl:call-template name="classifier"/></dt>
	</xsl:template>

	<xsl:template name="classifier">
	    <xsl:for-each select="../classifier">
			<span class="classifier"><xsl:apply-templates/></span>
		</xsl:for-each>
	</xsl:template>
	
	<xsl:template match="classifier">
		<!-- do nothing -->
	</xsl:template>

	<xsl:template match="definition">
		<dd class="definition"><xsl:apply-templates/></dd>
	</xsl:template>

</xsl:stylesheet>