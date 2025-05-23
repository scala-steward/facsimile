//======================================================================================================================
// Facsimile: A Discrete-Event Simulation Library
// Copyright © 2004-2025, Michael J Allen.
//
// This file is part of Facsimile.
//
// Facsimile is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
// version.
//
// Facsimile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
// warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
// details.
//
// You should have received a copy of the GNU Lesser General Public License along with Facsimile. If not, see:
//
//   http://www.gnu.org/licenses/lgpl.
//
// The developers welcome all comments, suggestions and offers of assistance. For further information, please visit the
// project home page at:
//
//   http://facsim.org/
//
// Thank you for your interest in the Facsimile project!
//
// IMPORTANT NOTE: All patches (modifications to existing files and/or the addition of new files) submitted for
// inclusion as part of the official Facsimile code base, must comply with the published Facsimile Coding Standards. If
// your code fails to comply with the standard, then your patches will be rejected. For further information, please
// visit the coding standards at:
//
//   http://facsim.org/Documentation/CodingStandards/
//======================================================================================================================

//======================================================================================================================
// Scala source file belonging to the org.facsim.util.test package.
//======================================================================================================================
package org.facsim.util.test

import java.util.{GregorianCalendar, Locale, MissingResourceException}
import org.facsim.util.Resource
import org.scalatest.funspec.AnyFunSpec

/** Test harness for the [[Resource]] class.
 */
final class ResourceTest
extends AnyFunSpec, CommonTestMethods:

  // Some commonly used strings.
  trait testResources:
    val compoundResource = Array("testCompoundResource0", "testCompoundResource1", "testCompoundResource2")
    val dateResource = "testDateResource"
    val helloResource = "testHelloResource"
    val realResource = "testRealResource"
    val singleResource = "testSingleResource"
    val testBundleName = "facsimile-util-test"

  // Name the class we're testing.
  describe(classOf[Resource].getCanonicalName.nn):

    // Test primary constructor operation.
    describe("this(String)"):
      it("must throw MissingResourceException when bundleName identifies a missing resource bundle"):
        intercept[MissingResourceException]:
          new Resource("NameOfNonExistentBundle")
      it("must find a defined resource bundle OK (without an exception)"):
        new testResources:
          new Resource(testBundleName)

    // Test string resource formatting.
    describe("apply(String, Any*)"):
      it("must throw MissingResourceException when key undefined"):
        new testResources:
          intercept[MissingResourceException]:
            new Resource(testBundleName).apply("UNDEFINED_KEY")
      ignore("must throw ClassCastException when key identifies non-string resource"):
        new testResources:

          // Cannot define non-string resources currently - test ignored
          intercept[ClassCastException]:
            new Resource(testBundleName).apply("testNonStringResource")
      ignore("must throw IllegalArgumentException when argument passed to non-compound resource"):
        new testResources:

          // Java doesn't throw an exception in this case - test ignored.
          intercept[IllegalArgumentException]:
            new Resource(testBundleName).apply(singleResource, "Invalid extra argument")
      ignore("must throw IllegalArgumentException when no argument passed to compound resource"):
        new testResources:

          // Java doesn't throw an exception in this case - test ignored.
          intercept[IllegalArgumentException]:
            new Resource(testBundleName).apply(compoundResource(0))
      ignore("must throw IllegalArgumentException when extra arguments passed to compound resource"):
        new testResources:

          // Java doesn't throw an exception in this case - test ignored.
          intercept[IllegalArgumentException]:
            new Resource(testBundleName).apply(compoundResource(0), "Valid argument", "Invalid extra argument")
      ignore("must throw IllegalArgumentException when insufficient arguments passed to compound resource"):
        new testResources:

          // Java doesn't throw an exception in this case - test ignored.
          intercept[IllegalArgumentException]:
            new Resource(testBundleName).apply(compoundResource(1), "Valid argument")
      it("must throw IllegalArgumentException when string passed to date resource"):
        new testResources:
          intercept[IllegalArgumentException]:
            new Resource(testBundleName).apply(dateResource, "Bob")
      it("must throw IllegalArgumentException when string passed to real resource"):
        new testResources:
          intercept[IllegalArgumentException]:
            new Resource(testBundleName).apply(realResource, "Fred")
      it("must retrieve a non-compound resource with no arguments OK"):
        new testResources:
          assert(new Resource(testBundleName).apply(singleResource) === "Test non-compound resource")
      it("must retrieve compound resources with appropriate arguments OK"):
        new testResources:
          val resource = new Resource(testBundleName)
          assert(resource.apply(compoundResource(0), "zero") === "Test compound resource 0: 0=zero")
          assert(resource.apply(compoundResource(1), "zero", "one") === "Test compound resource 1: 0=zero, 1=one")
          assert(resource.apply(compoundResource(2), "zero", "one", "two") ===
          "Test compound resource 2: 0=zero, 1=one, 2=two")
      it("must parse choice resources OK"):
        new testResources:
          val choiceResource = "testChoiceResource"
          assert(new Resource(testBundleName).apply(choiceResource, 0) === "On your marks...")
          assert(new Resource(testBundleName).apply(choiceResource, 1) === "Get set...")
          assert(new Resource(testBundleName).apply(choiceResource, 2) === "Go!")
      it("must retrieve localized string resources OK"):
        new testResources:
          // Check that we get the correct en_US response.
          withLocale(Locale.US.nn):
            assert(new Resource(testBundleName).apply(helloResource) === "Howdy!")

          // Should get a different result for Brits...
          withLocale(Locale.UK.nn):
            assert(new Resource(testBundleName).apply(helloResource) === "Wotcha!")

          // Germans should have a good day...
          withLocale(Locale.GERMANY.nn):
            assert(new Resource(testBundleName).apply(helloResource) === "Guten Tag!")

          // ...and so should the French...
          withLocale(Locale.FRANCE.nn):
            assert(new Resource(testBundleName).apply(helloResource) === "Bonjour!")

          // ...and anyone who speaks Spanish...
          withLocale(new Locale("es")):
            assert(new Resource(testBundleName).apply(helloResource) === "¡Hola!")
      it("must retrieve numeric resources OK"):
        new testResources:
          val number = 1234.56

          // Check that we get the correct en_US response.
          withLocale(Locale.US.nn):
            assert(new Resource(testBundleName).apply(realResource, number) === "1,234.56")

          // Germans do things differently...
          withLocale(Locale.GERMANY.nn):
            assert(new Resource(testBundleName).apply(realResource, number) === "1.234,56")
      it("must retrieve localized date resources OK"):
        new testResources:

          // Sep 14, 2010 - months are zero-based numbers(!).
          //
          // Note that dates are formatted according to the "short" date format.
          val date = new GregorianCalendar(2010, 8, 14).getTime

          // Check that we get the correct en_US response.
          withLocale(Locale.US.nn):
            assert(new Resource(testBundleName).apply(dateResource, date) === "9/14/10")

          // Month & day are in different order in the UK. Also, month has leading zero, and years are 4 digit.
          withLocale(Locale.UK.nn):
            assert(new Resource(testBundleName).apply(dateResource, date) === "14/09/2010")

          // Germans use same order as UK, but different separator. Years are 2 digits.
          withLocale(Locale.GERMANY.nn):
            assert(new Resource(testBundleName).apply(dateResource, date) === "14.09.10")
