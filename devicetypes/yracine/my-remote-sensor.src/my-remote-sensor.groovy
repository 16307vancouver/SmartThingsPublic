/**
 *  My Remote Sensor
 *  v2.0
 *
 *  Copyright 2015-2020 Yves Racine
 *  LinkedIn profile: ca.linkedin.com/pub/yves-racine-m-sc-a/0/406/4b/
 *
 *  Developer retains all right, title, copyright, and interest, including all copyright, patent rights, trade secret 
 *  in the Background technology. May be subject to consulting fees under the Agreement between the Developer and the Customer. 
 *  Developer grants a non exclusive perpetual license to use the Background technology in the Software developed for and delivered 
 *  to Customer under this Agreement. However, the Customer shall make no commercial use of the Background technology without
 *  Developer's written consent.
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
  *  Software Distribution is restricted and shall be done only with Developer's written approval.
 *
 *  For installation, please refer to readme file under
 *     https://github.com/yracine/device-type.myecobee/blob/master/smartapps/readme.ecobee3RemoteSensor
 *
 */
metadata {
	// Automatically generated. Make future change here.
	definition (name: "My Remote Sensor", namespace: "yracine",  mnmn: "SmartThingsCommunity",vid: "b36d9bf1-efe0-382c-a4fd-351e2fb19ae0", ocfDeviceType:"x.com.st.d.sensor.temperature", author: "Yves Racine") {
		// vid: "6fb2e5c0-f83c-3384-83bb-f65e8fa88943"
		capability "Temperature Measurement"
//		capability "Relative Humidity Measurement"
		capability "Sensor"	
		capability "Motion Sensor"
		capability "Battery" // Not available at ecobee, to be added later        
	}
	attribute "name", "string"
	attribute "temperatureDisplay", "string"
    
	// simulator metadata
	simulator {
		for (int i = 0; i <= 100; i += 10) {
			status "${i}F": "temperature: $i F"
		}

		for (int i = 0; i <= 100; i += 10) {
			status "${i}%": "humidity: ${i}%"
		}
	}

	// UI tile definitions
	tiles(scale: 2) {
		multiAttributeTile(name:"motion", type: "generic", width: 2, height: 2,canChangeIcon: true){
			tileAttribute ("device.motion", key: "PRIMARY_CONTROL") {
				attributeState "active", label:'motion', icon:"st.motion.motion.active", backgroundColor:"#53a7c0"
				attributeState "inactive", label:'no motion', icon:"st.motion.motion.inactive", backgroundColor:"#ffffff"
			}
		}
		valueTile("temperature", "device.temperatureDisplay", width: 6, height: 4, canChangeIcon: true) {
			state("temperature", label:'${currentValue}', unit:"F",
				backgroundColors:[
					// Celsius Color Range
					[value: 0, color: "#153591"],, 
					[value: 7, color: "#1e9cbb"],
					[value: 15, color: "#90d2a7"],
					[value: 23, color: "#44b621"],
					[value: 29, color: "#f1d801"],
					[value: 33, color: "#d04e00"],
					[value: 36, color: "#bc2323"],
					// Fahrenheit Color Range
					[value: 40, color: "#153591"],
					[value: 44, color: "#1e9cbb"],
					[value: 59, color: "#90d2a7"],
					[value: 74, color: "#44b621"],
					[value: 84, color: "#f1d801"],
					[value: 92, color: "#d04e00"],
					[value: 96, color: "#bc2323"]
				]
			)
		}
		valueTile("name", "device.name", inactiveLabel: false, width: 6, height: 1) {
			state "default", label: '${currentValue}', 
			backgroundColor: "#ffffff"
		}
//		valueTile("humidity", "device.humidity", inactiveLabel: false) {
//			state "humidity", label:'${currentValue}% humidity', unit:""
//		}
        
	main(["temperature"])
		details(["name","temperature", "motion"])
//		details(["name","temperature", "motion", "humidity"])
	}
}

// Parse incoming device messages to generate events
def parse(String description) {
	def name = parseName(description)
	def value = parseValue(description)
	def unit = name == "temperature" ? getTemperatureScale() : (name == "humidity" ? "%" : null)
	def result = createEvent(name: name, value: value, unit: unit)
	log.debug "Parse returned ${result?.descriptionText}"
	return result
}

private String parseName(String description) {
	if (description?.startsWith("temperature: ")) {
		return "temperature"
	} else if (description?.startsWith("humidity: ")) {
		return "humidity"
	} else if (description?.startsWith("name: ")) {
		return "name"
	}        
	null
}

private String parseValue(String description) {
	if (description?.startsWith("temperature: ")) {
		return zigbee.parseHATemperatureValue(description, "temperature: ", getTemperatureScale())
	} else if (description?.startsWith("humidity: ")) {
		def pct = (description - "humidity: " - "%").trim()
		if (pct.isNumber()) {
			return Math.round(new BigDecimal(pct)).toString()
		}
	} else if (description?.startsWith("name: ")) {
		return  (description - "name: ").trim()
	}        
	null
}

//remove from the selected devices list in Service Manager
void uninstalled() {
	parent.purgeChildDevice(this)    
}