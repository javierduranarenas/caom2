/*
************************************************************************
*******************  CANADIAN ASTRONOMY DATA CENTRE  *******************
**************  CENTRE CANADIEN DE DONNÉES ASTRONOMIQUES  **************
*
*  (c) 2011.                            (c) 2011.
*  Government of Canada                 Gouvernement du Canada
*  National Research Council            Conseil national de recherches
*  Ottawa, Canada, K1A 0R6              Ottawa, Canada, K1A 0R6
*  All rights reserved                  Tous droits réservés
*
*  NRC disclaims any warranties,        Le CNRC dénie toute garantie
*  expressed, implied, or               énoncée, implicite ou légale,
*  statutory, of any kind with          de quelque nature que ce
*  respect to the software,             soit, concernant le logiciel,
*  including without limitation         y compris sans restriction
*  any warranty of merchantability      toute garantie de valeur
*  or fitness for a particular          marchande ou de pertinence
*  purpose. NRC shall not be            pour un usage particulier.
*  liable in any event for any          Le CNRC ne pourra en aucun cas
*  damages, whether direct or           être tenu responsable de tout
*  indirect, special or general,        dommage, direct ou indirect,
*  consequential or incidental,         particulier ou général,
*  arising from the use of the          accessoire ou fortuit, résultant
*  software.  Neither the name          de l'utilisation du logiciel. Ni
*  of the National Research             le nom du Conseil National de
*  Council of Canada nor the            Recherches du Canada ni les noms
*  names of its contributors may        de ses  participants ne peuvent
*  be used to endorse or promote        être utilisés pour approuver ou
*  products derived from this           promouvoir les produits dérivés
*  software without specific prior      de ce logiciel sans autorisation
*  written permission.                  préalable et particulière
*                                       par écrit.
*
*  This file is part of the             Ce fichier fait partie du projet
*  OpenCADC project.                    OpenCADC.
*
*  OpenCADC is free software:           OpenCADC est un logiciel libre ;
*  you can redistribute it and/or       vous pouvez le redistribuer ou le
*  modify it under the terms of         modifier suivant les termes de
*  the GNU Affero General Public        la “GNU Affero General Public
*  License as published by the          License” telle que publiée
*  Free Software Foundation,            par la Free Software Foundation
*  either version 3 of the              : soit la version 3 de cette
*  License, or (at your option)         licence, soit (à votre gré)
*  any later version.                   toute version ultérieure.
*
*  OpenCADC is distributed in the       OpenCADC est distribué
*  hope that it will be useful,         dans l’espoir qu’il vous
*  but WITHOUT ANY WARRANTY;            sera utile, mais SANS AUCUNE
*  without even the implied             GARANTIE : sans même la garantie
*  warranty of MERCHANTABILITY          implicite de COMMERCIALISABILITÉ
*  or FITNESS FOR A PARTICULAR          ni d’ADÉQUATION À UN OBJECTIF
*  PURPOSE.  See the GNU Affero         PARTICULIER. Consultez la Licence
*  General Public License for           Générale Publique GNU Affero
*  more details.                        pour plus de détails.
*
*  You should have received             Vous devriez avoir reçu une
*  a copy of the GNU Affero             copie de la Licence Générale
*  General Public License along         Publique GNU Affero avec
*  with OpenCADC.  If not, see          OpenCADC ; si ce n’est
*  <http://www.gnu.org/licenses/>.      pas le cas, consultez :
*                                       <http://www.gnu.org/licenses/>.
*
*  $Revision: 5 $
*
************************************************************************
*/

package ca.nrc.cadc.caom2.compute;

import ca.nrc.cadc.caom2.Artifact;
import ca.nrc.cadc.caom2.Chunk;
import ca.nrc.cadc.caom2.Part;
import ca.nrc.cadc.caom2.ProductType;
import ca.nrc.cadc.caom2.ReleaseType;
import ca.nrc.cadc.caom2.types.Circle;
import ca.nrc.cadc.caom2.types.Interval;
import ca.nrc.cadc.caom2.types.Point;
import ca.nrc.cadc.caom2.wcs.Axis;
import ca.nrc.cadc.caom2.wcs.Coord2D;
import ca.nrc.cadc.caom2.wcs.CoordAxis1D;
import ca.nrc.cadc.caom2.wcs.CoordAxis2D;
import ca.nrc.cadc.caom2.wcs.CoordBounds1D;
import ca.nrc.cadc.caom2.wcs.CoordCircle2D;
import ca.nrc.cadc.caom2.wcs.CoordFunction1D;
import ca.nrc.cadc.caom2.wcs.CoordFunction2D;
import ca.nrc.cadc.caom2.wcs.CoordPolygon2D;
import ca.nrc.cadc.caom2.wcs.CoordRange1D;
import ca.nrc.cadc.caom2.wcs.CoordRange2D;
import ca.nrc.cadc.caom2.wcs.Dimension2D;
import ca.nrc.cadc.caom2.wcs.ObservableAxis;
import ca.nrc.cadc.caom2.wcs.PolarizationWCS;
import ca.nrc.cadc.caom2.wcs.RefCoord;
import ca.nrc.cadc.caom2.wcs.Slice;
import ca.nrc.cadc.caom2.wcs.SpatialWCS;
import ca.nrc.cadc.caom2.wcs.SpectralWCS;
import ca.nrc.cadc.caom2.wcs.TemporalWCS;
import ca.nrc.cadc.caom2.wcs.ValueCoord2D;
import ca.nrc.cadc.util.Log4jInit;
import java.net.URI;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author pdowler
 */
public class CutoutUtilTest 
{
    private static final Logger log = Logger.getLogger(CutoutUtilTest.class);

    static
    {
        Log4jInit.setLevel("ca.nrc.cadc.caom2", Level.INFO);
    }

    //@Test
    public void testTemplate()
    {
        try
        {

        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
    }
    
    @Test
    public void testIllegalArgs()
    {
        try
        {
            try 
            { 
                CutoutUtil.computeCutout(null, new Circle(new Point(12, 34), 1), null, null, null); 
                Assert.fail("expected IllegalArgumentException for null artifact");
            }
            catch(IllegalArgumentException expected) { }
            
            // this is not testable without part and chunk metadata because the Shape->impl support is in a wcs-specific
            // piece of code
            //try 
            //{ 
            //    CutoutUtil.computeCutout(new Artifact(new URI("ad", "FOO/bar", null)), new Location(new Point(1.0, 2.0)), null, null, null); 
            //    Assert.fail("expected IllegalArgumentException for Location cutout");
            //}
            //catch(IllegalArgumentException expected) { }
            
        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
    }
    
    @Test
    public void testComputeCutoutAll()
    {
        try
        {
            Chunk c = new Chunk();
            c.position = new SpatialWCS(new CoordAxis2D(new Axis("RA", "deg"), new Axis("DEC", "deg")));
            c.position.coordsys = "ICRS";
            c.position.getAxis().function = new CoordFunction2D(
                    new Dimension2D(256, 256), 
                    new Coord2D(new RefCoord(128.5, 10.0), new RefCoord(128.5, 10.0)),
                    1.0e-3, 0.0, 0.0, 1.0e-3);

            c.energy = new SpectralWCS(new CoordAxis1D(new Axis("WAVE", "nm")),"TOPOCENT");
            c.energy.getAxis().function = new CoordFunction1D(2500L, 0.1, new RefCoord(0.5, 300.0));  // 300-550 nm

            c.time = new TemporalWCS(new CoordAxis1D(new Axis("TIME", "d")));
            c.time.timesys = "UTC";
            c.time.getAxis().function = new CoordFunction1D(100L, 1.0, new RefCoord(0.5, 55000.0));   // 55000 to 55100 days

            c.polarization = new PolarizationWCS(new CoordAxis1D(new Axis("STOKES", null)));
            c.polarization.getAxis().function = new CoordFunction1D(3L, 1.0, new RefCoord(0.5, 1.0)); // IQU

            c.observable = new ObservableAxis(new Slice(new Axis("stuff", "quatloobs"), 1L));
            
            // axes are not bound to specific dimensions 1-5
            Assert.assertFalse(CutoutUtil.canCutout(c));
            
            c.positionAxis1 = 1;
            c.positionAxis2 = 2;
            c.energyAxis = 3;
            c.timeAxis = 4;
            c.polarizationAxis = 5;
            c.observableAxis = 6;
            
            Artifact a = new Artifact(new URI("ad", "FOO/bar", null), ProductType.SCIENCE, ReleaseType.DATA);
            Part p = new Part(0);
            a.getParts().add(p);
            p.getChunks().add(c);
            List<String> cus;
            
            String tmpl = "[0][STAR]";
            String cur = "";
            for (int i=1; i<=6; i++)
            {
                c.naxis = i;
                if (i > 1)
                    cur += ",";
                if (i == 6)
                    cur += "1:1"; // internal observable cut 
                else
                    cur += "*";
                String expected = tmpl.replace("STAR", cur);
                cus = CutoutUtil.computeCutout(a, null, null, null, null);
                Assert.assertNotNull(cus);
                Assert.assertTrue(cus.size() == 1);
                String cutout = cus.get(0);
                log.debug("cutout " + i + ":" + cutout);
                Assert.assertEquals("cutout " + i, expected, cutout); // [part name][*,*] for all pixels
            }
        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
    }
    
    @Test
    public void testCutoutPosition()
    {
        try
        {
            Chunk c = new Chunk();
            Assert.assertFalse(CutoutUtil.canCutout(c));
            
            c.position = new SpatialWCS(new CoordAxis2D(new Axis("RA", "deg"), new Axis("DEC", "deg")));
            c.position.coordsys = "ICRS";
            c.naxis = 2;
            Assert.assertFalse(CutoutUtil.canCutout(c));
            
            Coord2D start = new Coord2D(new RefCoord(0.5, 10.0), new RefCoord(0.5, 10.0));
            Coord2D end = new Coord2D(new RefCoord(256.0, 11.0), new RefCoord(256.0, 11.0));
            c.position.getAxis().range = new CoordRange2D(start, end);
            Assert.assertFalse(CutoutUtil.canCutout(c));
            
            c.position.getAxis().bounds = new CoordCircle2D(new ValueCoord2D(10.0, 11.0), 0.5);
            Assert.assertFalse(CutoutUtil.canCutout(c));
            
            CoordPolygon2D poly = new CoordPolygon2D();
            poly.getVertices().add(new ValueCoord2D(10.0, 10.0));
            poly.getVertices().add(new ValueCoord2D(11.0, 10.0));
            poly.getVertices().add(new ValueCoord2D(10.0, 11.0));
            c.position.getAxis().bounds = poly;
            Assert.assertFalse(CutoutUtil.canCutout(c));
            
            c.positionAxis1 = 1;
            c.positionAxis2 = 2;
            Assert.assertFalse(CutoutUtil.canCutout(c)); // no function
            
            CoordFunction2D wcsf = new CoordFunction2D(
                    new Dimension2D(256, 256), 
                    new Coord2D(new RefCoord(128.5, 10.0), new RefCoord(128.5, 10.0)),
                    1.0e-3, 0.0, 0.0, 1.0e-3);
            c.position.getAxis().function = wcsf;
            Assert.assertTrue("can cutout", CutoutUtil.canCutout(c));
            
            // axes are meta only
            c.positionAxis1 = 3;
            c.positionAxis2 = 4;
            Assert.assertFalse(CutoutUtil.canCutout(c)); // no function
            
            // not enough axes in data
            c.naxis = 1;
            c.positionAxis1 = 1;
            c.positionAxis2 = 2;
            
            Assert.assertFalse(CutoutUtil.canCutout(c)); // no function
            
            c.naxis = 2;
            Assert.assertTrue("can cutout", CutoutUtil.canCutout(c));
            
            // cleanup so these don't effect cutouts
            c.position.getAxis().range = null;
            c.position.getAxis().bounds = null;
            
            Artifact a = new Artifact(new URI("ad", "FOO/bar", null), ProductType.SCIENCE, ReleaseType.DATA);
            Part p = new Part(0);
            a.getParts().add(p);
            p.getChunks().add(c);
            
            Circle miss = new Circle(new Point(2.0, 2.0), 0.1);
            Circle inside = new Circle(new Point(10.0, 10.0), 1.0e-4);
            Circle outside = new Circle(new Point(10.0, 10.0), 1.0);
            
            List<String> cus;
            
            cus = CutoutUtil.computeCutout(a, miss, null, null, null);
            Assert.assertNotNull(cus);
            Assert.assertTrue(cus.isEmpty());
            
            cus = CutoutUtil.computeCutout(a, inside, null, null, null);
            Assert.assertNotNull(cus);
            Assert.assertEquals(1, cus.size());
            String cutout = cus.get(0);
            Assert.assertEquals("[0][128:128,128:128]", cutout); // one pixel in the middle of part [0]
            
            cus = CutoutUtil.computeCutout(a, outside, null, null, null);
            Assert.assertNotNull(cus);
            Assert.assertTrue(cus.size() == 1);
            cutout = cus.get(0);
            Assert.assertEquals("[0][*,*]", cutout); // [part name][*,*] for all pixels
        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
    }
    
    @Test
    public void testCutoutPositionGAL()
    {
        try
        {
            Chunk c = new Chunk();
            Assert.assertFalse(CutoutUtil.canCutout(c));
            
            c.position = new SpatialWCS(new CoordAxis2D(new Axis("GLON", "deg"), new Axis("GLAT", "deg")));
            c.naxis = 2;
            Assert.assertFalse(CutoutUtil.canCutout(c));
            
            
            c.positionAxis1 = 1;
            c.positionAxis2 = 2;
            Assert.assertFalse(CutoutUtil.canCutout(c)); // no function
            
            CoordFunction2D wcsf = new CoordFunction2D(
                    new Dimension2D(256, 256), 
                    new Coord2D(new RefCoord(128.5, 10.0), new RefCoord(128.5, 10.0)),
                    1.0e-3, 0.0, 0.0, 1.0e-3);
            c.position.getAxis().function = wcsf;
            Assert.assertTrue("can cutout", CutoutUtil.canCutout(c));
            
            Artifact a = new Artifact(new URI("ad", "FOO/bar", null), ProductType.SCIENCE, ReleaseType.DATA);
            Part p = new Part(0);
            a.getParts().add(p);
            p.getChunks().add(c);
            
            Circle miss = new Circle(new Point(2.0, 2.0), 0.1);
            Circle inside = new Circle(new Point(262.89, -15.21), 1.0e-4); // 10,10 in gal ~~ 262,-15 in ICRS
            Circle outside = new Circle(new Point(262.89, -15.21), 1.0);
            
            List<String> cus = CutoutUtil.computeCutout(a, miss, null, null, null);
            Assert.assertNotNull(cus);
            Assert.assertTrue(cus.isEmpty());
            
            cus = CutoutUtil.computeCutout(a, inside, null, null, null);
            Assert.assertNotNull(cus);
            Assert.assertTrue(cus.size() == 1);
            String cutout = cus.get(0);
            Assert.assertEquals("[0][125:125,129:129]", cutout); // one pixel approximately in the middle of part [0]
            
            cus = CutoutUtil.computeCutout(a, outside, null, null, null);
            Assert.assertNotNull(cus);
            Assert.assertTrue(cus.size() == 1);
            cutout = cus.get(0);
            Assert.assertEquals("[0][*,*]", cutout); // [part name][*,*] for all pixels
        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
    }
    
    @Test
    public void testCanCutoutEnergy()
    {
        try
        {
            Chunk c = new Chunk();
            Assert.assertFalse(CutoutUtil.canCutout(c));
            
            c.energy = new SpectralWCS(new CoordAxis1D(new Axis("WAVE", "nm")),"TOPOCENT");
            c.naxis = 1;
            Assert.assertFalse(CutoutUtil.canCutout(c));
            
            c.energy.getAxis().range = new CoordRange1D(new RefCoord(0.5, 300.0), new RefCoord(256.5, 550.0));
            Assert.assertFalse(CutoutUtil.canCutout(c));
            
            CoordBounds1D bounds = new CoordBounds1D();
            bounds.getSamples().add(new CoordRange1D(new RefCoord(0.5, 300.0), new RefCoord(40.0, 350.0)));
            bounds.getSamples().add(new CoordRange1D(new RefCoord(80.0, 400.0), new RefCoord(120.0, 450.0)));
            bounds.getSamples().add(new CoordRange1D(new RefCoord(220.0, 500.0), new RefCoord(256.5, 550.0)));
            Assert.assertFalse(CutoutUtil.canCutout(c));
            
            CoordFunction1D function = new CoordFunction1D(2500L, 0.1, new RefCoord(0.5, 300.0));
            Assert.assertFalse(CutoutUtil.canCutout(c));
            
            c.energyAxis = 1;
            Assert.assertFalse(CutoutUtil.canCutout(c));
            
            // bounds cutout
            c.energy.getAxis().function = null;
            c.energy.getAxis().bounds = bounds;
            Assert.assertTrue("can cutout", CutoutUtil.canCutout(c));
            
            // function cutout
            c.energy.getAxis().function = function;
            c.energy.getAxis().bounds = null;
            Assert.assertTrue("can cutout", CutoutUtil.canCutout(c));
            
            // metadata only
            c.energyAxis = null;
            Assert.assertFalse(CutoutUtil.canCutout(c));
            
            c.energyAxis = 2;
            Assert.assertFalse(CutoutUtil.canCutout(c));
            
            // restore working structure
            c.energyAxis = 1;
            Assert.assertTrue("can cutout", CutoutUtil.canCutout(c));
            
            // cleanup so these don't effect cutouts
            c.energy.getAxis().range = null;
            c.energy.getAxis().bounds = null;
            
            Artifact a = new Artifact(new URI("ad", "FOO/bar", null), ProductType.SCIENCE, ReleaseType.DATA);
            Part p = new Part(0);
            a.getParts().add(p);
            p.getChunks().add(c);
            
            // cutout requests: must be wavelength in meters
            Interval miss = new Interval(600.0e-9, 800.0e-9);
            Interval inside = new Interval(440.0e-9, 480.0e-9);
            Interval outside = new Interval(200.0e-9, 900.0e-9);
            
            List<String> cus = CutoutUtil.computeCutout(a, null, miss, null, null);
            Assert.assertNotNull(cus);
            Assert.assertTrue(cus.isEmpty());
            
            cus = CutoutUtil.computeCutout(a, null, inside, null, null);
            Assert.assertNotNull(cus);
            Assert.assertTrue(cus.size() == 1);
            String cutout = cus.get(0);
            log.debug("energy cutout: " + cutout);
            Assert.assertEquals("[0][1400:1800]", cutout);
            
            cus = CutoutUtil.computeCutout(a, null, outside, null, null);
            Assert.assertNotNull(cus);
            Assert.assertTrue(cus.size() == 1);
            cutout = cus.get(0);
            log.debug("energy cutout: " + cutout);
            Assert.assertEquals("[0][*]", cutout); // one pixel in the middle of part [0]
        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
    }
    
    @Test
    public void testCanCutoutTime()
    {
        try
        {

        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
    }
    
    @Test
    public void testCanCutoutPolarization()
    {
        try
        {

        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
    }
    
    @Test
    public void testCanCutoutObservable()
    {
        try
        {
            Chunk c = new Chunk();
            Assert.assertFalse(CutoutUtil.canObservableCutout(c));
            
            Slice s1 = new Slice(new Axis("foo", "m"), 1L);
            Slice s2 = new Slice(new Axis("bar", "s"), 2L);
            
            c.observable = new ObservableAxis(s1);
            Assert.assertFalse(CutoutUtil.canObservableCutout(c));
            
            c.naxis = 1;
            Assert.assertFalse(CutoutUtil.canObservableCutout(c));
            
            c.observableAxis = 2;
            Assert.assertFalse(CutoutUtil.canObservableCutout(c));
            
            c.observableAxis = 1;
            Assert.assertTrue(CutoutUtil.canObservableCutout(c));
            
            c.observable.independent = s2;
            Assert.assertTrue(CutoutUtil.canObservableCutout(c));
            
        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
    }
}
