/*
 * Copyright 2019 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package datatypes

import format_strings.TestFormatStringsRegistry
import io.jmix.core.CoreConfiguration
import io.jmix.core.Messages
import io.jmix.core.metamodel.datatype.impl.BigDecimalDatatype
import io.jmix.core.security.InMemoryUserRepository
import io.jmix.core.security.SystemAuthenticator
import io.jmix.core.security.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import test_support.TestContextInititalizer
import test_support.TestCoreProperties
import test_support.addon1.TestAddon1Configuration
import test_support.app.TestAppConfiguration
import test_support.base.TestBaseConfiguration

import java.text.ParseException

class BigDecimalDatatypeTest extends TestAuthenticator  {

    def "format/parse without locale, without rounding"() {
        def datatype = new BigDecimalDatatype()
        datatype.coreProperties = TestCoreProperties.builder()
                .setRoundDecimalValueByFormat(false)
                .build()

        expect:

        datatype.format(new BigDecimal('12345678.12345678')) == '12345678.1235'
        datatype.parse('12345678.12345678') == new BigDecimal('12345678.12345678')
        datatype.parse('12345678.456789') == new BigDecimal('12345678.456789')
    }

    def "format/parse without locale, with rounding"() {
        def datatype = new BigDecimalDatatype()
        datatype.coreProperties = TestCoreProperties.builder()
                .setRoundDecimalValueByFormat(true)
                .build()

        expect:

        datatype.format(new BigDecimal('12345678.12345678')) == '12345678.1235'
        datatype.parse('12345678.12345678') == new BigDecimal('12345678.1235')
        datatype.parse('12345678.456789') == new BigDecimal('12345678.4568')
    }

    def "format/parse with locale, without rounding"() {
        def datatype = new BigDecimalDatatype()
        datatype.formatStringsRegistry = new TestFormatStringsRegistry()
        datatype.coreProperties = TestCoreProperties.builder()
                .setRoundDecimalValueByFormat(false)
                .build()

        expect:

        datatype.format(new BigDecimal('12345678.12345678'), Locale.ENGLISH) == '12,345,678.12'
        datatype.parse('12345678.12345678', Locale.ENGLISH) == new BigDecimal('12345678.12345678')
        datatype.parse('12345678.456789', Locale.ENGLISH) == new BigDecimal('12345678.456789')
        datatype.parse('12,345,678.12345678', Locale.ENGLISH) == new BigDecimal('12345678.12345678')

    }

    def "format/parse with locale, with rounding"() {
        def datatype = new BigDecimalDatatype()
        datatype.formatStringsRegistry = new TestFormatStringsRegistry()
        datatype.coreProperties = TestCoreProperties.builder()
                .setRoundDecimalValueByFormat(true)
                .build()

        expect:

        datatype.format(new BigDecimal('12345678.12345678'), Locale.ENGLISH) == '12,345,678.12'
        datatype.parse('12345678.12345678', Locale.ENGLISH) == new BigDecimal('12345678.12')
        datatype.parse('12345678.456789', Locale.ENGLISH) == new BigDecimal('12345678.46')
        datatype.parse('12,345,678.12345678', Locale.ENGLISH) == new BigDecimal('12345678.12')
    }

    def "parse error due to unknown separators"() {
        createTestUser()

        def datatype = new BigDecimalDatatype()
        datatype.formatStringsRegistry = new TestFormatStringsRegistry()
        datatype.setMessages(messages)

        when:

        datatype.parse('12 345 678,12345678', Locale.ENGLISH) == new BigDecimal('12345678.12345678')

        then:

        removeTestUser()
        thrown(ParseException)
    }
}
