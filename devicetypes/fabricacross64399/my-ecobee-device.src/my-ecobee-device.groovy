/***
 *  My Ecobee Device
 *  Copyright 2014-2020 Yves Racine
 *  LinkedIn profile: ca.linkedin.com/pub/yves-racine-m-sc-a/0/406/4b/
 *  Version 6.2.2
 *  Refer to readme file for installation instructions.
 *
 *  Developer retains all right, title, copyright, and interest, including all copyright, patent rights,
 *  trade secret in the Background technology. May be subject to consulting fees under an Agreement 
 *  between the Developer and the Customer. Developer grants a non exclusive perpetual license to use
 *  the Background technology in the Software developed for and delivered to Customer under this
 *  Agreement. However, the Customer shall make no commercial use of the Background technology without
 *  Developer's written consent.
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 *  
 *  Software Distribution is restricted and shall be done only with Developer's written approval.
 */
import java.text.SimpleDateFormat
include 'asynchttp_v1'
import groovy.json.*
import groovy.transform.Field

// for the UI

preferences {

//	Preferences are no longer required when created with the Service Manager (MyEcobeeInit).

	input("thermostatId", "text", title: "Serial #", description:
		"Leave it blank, the serial number of your thermostat\n(not needed when using MyEcobeeInit)")
	input("appKey", "text", title: "App Key", description:
		"Leave it blank, the application key given by Ecobee\n(not needed when using MyEcobeeInit")
	input("ecobeeType", "text", title: "ecobee Tstat Type", description:
		"Leave it blank, set it to registered (by default) or managementSet\n(not needed when using MyEcobeeInit)")
	input("holdType", "text", title: "holdType", description:
		"Set it to nextTransition or indefinite (latter by default)")
	input("trace", "bool", title: "trace", description:
		"Set it to true to enable tracing (no spaces) or leave it empty (no tracing)")
	input("logFilter", "number",title: "(1=ERROR only,2=<1+WARNING>,3=<2+INFO>,4=<3+DEBUG>,5=<4+TRACE>)",  range: "1..5",
 		description: "optional" )        
}
metadata {
	// Automatically generated. Make future change here.
	definition(name: "My Ecobee Device", author: "Yves Racine",mnmn: "SmartThingsCommunity",ocfDeviceType:"oic.d.thermostat", vid: "ae369427-375d-38bf-94b8-29862d3bbfad",namespace: "fabricacross64399") {
		//vid: 72f10115-5959-3880-9227-e2ec8f2adeed,ae369427-375d-38bf-94b8-29862d3bbfad
		capability "Sensor"
		capability "Actuator"
		capability "Thermostat"
		capability "thermostat Setpoint"
		capability "Thermostat Mode"
		capability "Thermostat Fan Mode"
		capability "Thermostat Operating State"        
		capability "Relative Humidity Measurement"
		capability "Temperature Measurement"
		capability "Polling"
		capability "Refresh"
		capability "Presence Sensor"
		capability "Health Check"
		capability "fabricacross64399.setThermostatClimate"      
		capability "fabricacross64399.setTargetHumidityLevel"      
   
		attribute "thermostatId", "string"
		attribute "thermostatName", "string"
		attribute "connected", "string"
		attribute "temperatureDisplay", "string"
		attribute "coolingSetpointDisplay", "string"
		attribute "heatingSetpointDisplay", "string"
      
		attribute "heatingSetpointRangeHigh", "string"
		attribute "heatingSetpointRangeLow", "string"
		attribute "coolingSetpointRangeHigh", "string"
		attribute "coolingSetpointRangeLow", "string"
		attribute "coolingSetpointRange", "VECTOR3"
		attribute "heatingSetpointRange", "VECTOR3"
		attribute "verboseTrace", "string"
		attribute "fanMinOnTime", "number"
		attribute "humidifierMode", "string"
		attribute "dehumidifierMode", "string"
		attribute "humidifierLevel", "number"
		attribute "dehumidifierLevel", "number"
		attribute "condensationAvoid", "string"
		attribute "groups", "string"
		attribute "equipmentStatus", "string"
		attribute "alerts", "string"
		attribute "alertText", "string"        
		attribute "programScheduleName", "string"
		attribute "programFanMode", "string"
		attribute "programType", "string"
		attribute "programCoolTemp", "string"
		attribute "programHeatTemp", "string"
		attribute "programCoolTempDisplay", "string"
		attribute "programHeatTempDisplay", "string"
		attribute "programEndTimeMsg", "string"
		attribute "weatherDateTime", "string"
		attribute "weatherSymbol", "string"
		attribute "weatherStation", "string"
		attribute "weatherCondition", "string"
		attribute "weatherTemperatureDisplay", "string"
		attribute "weatherPressure", "string"
		attribute "weatherRelativeHumidity", "number"
		attribute "weatherWindSpeed", "string"
		attribute "weatherWindDirection", "string"
		attribute "weatherPop", "number"
		attribute "weatherTempHigh", "string"
		attribute "weatherTempLow", "string"
		attribute "weatherTempHighDisplay", "string"
		attribute "weatherTempLowDisplay", "string"
		attribute "plugName", "string"
		attribute "plugState", "string"
		attribute "plugSettings", "string"
		attribute "hasHumidifier", "string"
		attribute "hasDehumidifier", "string"
		attribute "hasErv", "string"
		attribute "hasHrv", "string"
		attribute "hasBoiler", "string"
		attribute "hasElectric", "string"
		attribute "hasHeatPump", "string"
		attribute "hasForcedAir", "string"
		attribute "ventilatorMinOnTime", "string"
		attribute "ventilatorMode", "string"
		attribute "programNameForUI", "string"
		attribute "thermostatOperatingState", "string"        
		attribute "climateList", "string"	
		attribute "supportedClimatePrograms", "string"	
		attribute "modelNumber", "string"
		attribute "followMeComfort", "string"
		attribute "autoAway", "string"
		attribute "intervalRevision", "string"
		attribute "runtimeRevision", "string"
		attribute "thermostatRevision", "string"
		attribute "heatStages", "number"
		attribute "coolStages", "number"
		attribute "climateName", "string"
		attribute "setClimate", "string"
		attribute "auxMaxOutdoorTemp","string"
		attribute "stage1HeatingDifferentialTemp", "string"        
		attribute "stage1CoolingDifferentialTemp", "string"        
		attribute "stage1HeatingDissipationTime", "number"
		attribute "stage1CoolingDissipationTime", "number"
		attribute "dehumidifyWithAC", "string"        
		attribute "dehumidifyOvercoolOffset", "string"        
		attribute "playbackVolume", "string"	
		attribute "microphoneEnabled", "string"	
		attribute "soundAlertVolume", "string"	
		attribute "soundTickVolume"	, "string"
		attribute "voiceEngine", "string"
		attribute "backlightOffTime","string"       
		attribute "backlightSleepIntensity","string"        
		attribute "backlightOnIntensity","string"     
		attribute "heatCoolMinDelta", "string"        
		attribute "compressorProtectionMinTime", "string"          
		attribute "compressorProtectionMinTemp", "string"          

		// Report Runtime events
        
		attribute "auxHeat1RuntimeInPeriod", "number"
		attribute "auxHeat2RuntimeInPeriod", "number"
		attribute "auxHeat3RuntimeInPeriod", "number"
		attribute "compCool1RuntimeInPeriod", "number"
		attribute "compCool2RuntimeInPeriod", "number"
		attribute "dehumidifierRuntimeInPeriod", "number"
		attribute "humidifierRuntimeInPeriod", "number"
		attribute "ventilatorRuntimeInPeriod", "number"
		attribute "fanRuntimeInPeriod", "number"
		attribute "compHeat1RuntimeInPeriod", "number"
		attribute "compHeat2RuntimeInPeriod", "number"
		attribute "compHeat3RuntimeInPeriod", "number"

		attribute "auxHeat1RuntimeDaily", "number"
		attribute "auxHeat2RuntimeDaily", "number"
		attribute "auxHeat3RuntimeDaily", "number"
		attribute "compHeat1RuntimeDaily", "number"
		attribute "compHeat2RuntimeDaily", "number"
		attribute "compHeat3RuntimeDaily", "number"
		attribute "compCool1RuntimeDaily", "number"
		attribute "compCool2RuntimeDaily", "number"
		attribute "dehumidifierRuntimeDaily", "number"
		attribute "humidifierRuntimeDaily", "number"
		attribute "ventilatorRuntimeDaily", "number"
		attribute "fanRuntimeDaily", "number"
		attribute "reportData", "string"

		attribute "auxHeat1RuntimeYesterday", "number"
		attribute "auxHeat2RuntimeYesterday", "number"
		attribute "auxHeat3RuntimeYesterday", "number"
		attribute "compHeat1RuntimeYesterday", "number"
		attribute "compHeat2RuntimeYesterday", "number"
		attribute "compHeat3RuntimeYesterday", "number"
		attribute "compCool1RuntimeYesterday", "number"
		attribute "compCool2RuntimeYesterday", "number"

		attribute "auxHeat1RuntimeAvgWeekly", "number"
		attribute "auxHeat2RuntimeAvgWeekly", "number"
		attribute "auxHeat3RuntimeAvgWeekly", "number"
		attribute "compHeat1RuntimeAvgWeekly", "number"
		attribute "comHeat2RuntimeAvgWeekly", "number"
		attribute "compHeat3RuntimeAvgWeekly", "number"
		attribute "compCool1RuntimeAvgWeekly", "number"
		attribute "compCool2RuntimeAvgWeekly", "number"

		attribute "auxHeat1RuntimeAvgMonthly", "number"
		attribute "auxHeat2RuntimeAvgMonthly", "number"
		attribute "auxHeat3RuntimeAvgMonthly", "number"
		attribute "compHeat1RuntimeAvgMonthly", "number"
		attribute "compHeat2RuntimeAvgMonthly", "number"
		attribute "compHeat3RuntimeAvgMonthly", "number"
		attribute "compCool1RuntimeAvgMonthly", "number"
		attribute "compCool2RuntimeAvgMonthly", "number"


		// Report Sensor Data & Stats
		        
		attribute "reportSensorMetadata", "string"
		attribute "reportSensorData", "string"
		attribute "reportSensorAvgInPeriod", "number"
		attribute "reportSensorMinInPeriod", "number"
		attribute "reportSensorMaxInPeriod", "number"
		attribute "reportSensorTotalInPeriod", "number"
        
		// Remote Sensor Data & Stats

		attribute "remoteSensorData", "string"
		attribute "remoteSensorTmpData", "string"
		attribute "remoteSensorHumData", "string"
		attribute "remoteSensorOccData", "string"
		attribute "remoteSensorAvgTemp", "string"
		attribute "remoteSensorAvgHumidity", "number"
		attribute "remoteSensorMinTemp", "string"
		attribute "remoteSensorMinHumidity", "number"
		attribute "remoteSensorMaxTemp", "string"
		attribute "remoteSensorMaxHumidity","number"


		// Recommendations 
        
		attribute "tip1Text", "string"
		attribute "tip1Level", "string"
		attribute "tip1Solution", "string"
		attribute "tip2Text", "string"
		attribute "tip2Level", "string"
		attribute "tip2Solution", "string"
		attribute "tip3Text", "string"
		attribute "tip3Level", "string"
		attribute "tip3Solution", "string"
		attribute "tip4Text", "string"
		attribute "tip4Level", "string"
		attribute "tip4Solution", "string"
		attribute "tip5Text", "string"
		attribute "tip5Level", "string"
		attribute "tip5Solution", "string"
        
        

        command "setThermostatProgram", ["string"]
		command "setTargetHumidity", ["number"]
		command "levelUp"
		command "levelDown"
		command "switchMode"        
		command "setFanMinOnTime"
		command "setCondensationAvoid"
		command "createVacation"
		command "deleteVacation"
		command "getEcobeePinAndAuth"
		command "getThermostatInfo"
		command "getThermostatSummary"
		command "iterateCreateVacation"
		command "iterateDeleteVacation"
		command "iterateResumeProgram"
		command "iterateSetHold"
		command "resumeProgram"
		command "resumeThisTstat"        
		command "setHold"
		command "setHoldWithHoldType"
		command "setHoldExtraParams"
		command "heatLevelUp"
		command "heatLevelDown"
		command "coolLevelUp"
		command "coolLevelDown"
		command "auxHeatOnly"
		command "setThermostatFanMode"
		command "dehumidifierOff"
		command "dehumidifierOn"
		command "humidifierOff"
		command "humidifierAuto"
		command "humidifierManual"
		command "setHumidifierLevel"
		command "setDehumidifierLevel"
		command "updateGroup"
		command "getGroups"
		command "iterateUpdateGroup"
		command "createGroup"
		command "deleteGroup"
		command "updateClimate"
		command "iterateUpdateClimate"
		command "createClimate"
		command "deleteClimate"
		command "setClimate"
		command "setClimateWithHoldType"
		command "iterateSetClimate"
		command "controlPlug" 
		command "ventilatorOn"
		command "ventilatorAuto"
		command "ventilatorOff"
		command "setVentilatorMinOnTime"
		command "awake"
		command "away"
		command "present"
		command "home"
		command "asleep"
		command "quickSave"
		command "setThisTstatClimate"
		command "setThermostatSettings"
		command "iterateSetThermostatSettings"
		command "getEquipmentStatus"
		command "refreshChildTokens" 
		command "autoAway"
		command "followMeComfort"
		command "getReportData"
		command "generateReportRuntimeEvents"
		command "generateReportSensorStatsEvents"
		command "getThermostatRevision"
		command "generateRemoteSensorEvents"
		command "getRemoteSensorUpdate"   
		command "getTips"  
		command "resetTips"     
		command "getAlertText" 
		command "emergencyHeat"        
		command "setHeatingSetpointRangeHigh"
		command "setHeatingSetpointRangeLow"
		command "setCoolingSetpointRangeHigh"
		command "setCoolingSetpointRangeLow"
		command "setHeatingSetpointRange"
		command "setCoolingSetpointRange"
		command "setDehumidifyWithAC"    
		command "setDehumidifyACOffset"    
		command "produceSummaryReport"  	      
		command "updateAudio"		// only for the ecobee4 model
		command "setBacklightOffTime"
		command "setBacklightSleepIntensity"
		command "setBacklightOnIntensity"
		command "setHeatCoolMinDelta"  /* The minimum temperature difference between the heat and cool values*/      
		command "setStage1CoolDifferentialTemp"  /* The difference between current temperature and set-point that will trigger stage 2 cooling.   */      
		command "setStage1HeatDifferentialTemp"  /* The difference between current temperature and set-point that will trigger stage 2 heating.   */           
		command "setCoolDissipationTime"   /* The time after a cooling cycle that the fan will run for to extract any cooling  */
		command "setHeatDissipationTime"  /* The time after a heating cycle that the fan will run for to extract any cooling  */      
		command "setCompressorProtectionMinTemp" /*    The minimum outdoor temperature that the compressor can operate at  */
		command "setCompressorProtectionMinTime" /*    The minimum time the compressor must be off for in order to prevent short-cycling.  */
		command "setAuxMaxOutdoorTemp" /*  The maximum outdoor temperature above which aux heat will not run. */
        
        //		command "generateRTEvents" // Not supported yet
            
        
	}        
	simulator {
		// TODO: define status and reply messages here
	}

	tiles(scale: 2) {
    
		multiAttributeTile(name:"thermostatMulti", type:"thermostat", width:6, height:4, canChangeIcon: true) {
			tileAttribute("device.temperatureDisplay", key: "PRIMARY_CONTROL") {
				attributeState("default", label:'${currentValue}', unit:"dF", backgroundColor:"#269bd2") 
			}
			tileAttribute("device.thermostatSetpoint", key: "VALUE_CONTROL") {
				attributeState("default", action: "setTemperature")
				attributeState("VALUE_UP", action: "levelUp")
				attributeState("VALUE_DOWN", action: "levelDown")
			}
			tileAttribute("device.humidity", key: "SECONDARY_CONTROL") {
				attributeState("default", label:'${currentValue}%', unit:"%")
			}
			tileAttribute("device.thermostatOperatingState", key: "OPERATING_STATE") {
				attributeState("idle", backgroundColor:"#44b621")
				attributeState("heating", backgroundColor:"#ffa81e")
				attributeState("cooling", backgroundColor:"#269bd2")
			}
			tileAttribute("device.thermostatMode", key: "THERMOSTAT_MODE") {
				attributeState("off", label:'${name}')
				attributeState("heat", label:'${name}')
				attributeState("cool", label:'${name}')
				attributeState("auto", label:'${name}')
			}
			tileAttribute("device.heatingSetpointDisplay", key: "HEATING_SETPOINT") {
				attributeState("default", label:'${currentValue}', unit:"dF")
			}
			tileAttribute("device.coolingSetpointDisplay", key: "COOLING_SETPOINT") {
				attributeState("default", label:'${currentValue}', unit:"dF")
			}
		}
		valueTile("temperatureDisplay", "device.temperatureDisplay", width: 2, height: 2) {
			state("temperatureDisplay", label:'${currentValue}', unit:"dF",
			backgroundColors: getBackgroundColors())
		}
		valueTile("name", "device.thermostatName", inactiveLabel: false, width: 2,
			height: 2) {
			state "default", label: '${currentValue}', 
			backgroundColor: "#ffffff"
		}
/*        
		standardTile("heatLevelUp", "device.heatingSetpoint", width: 3, height: 1, canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
			state "heatLevelUp", label:'Heat', action:"heatLevelUp", icon:"http://raw.githubusercontent.com/yracine/device-type.myecobee/master/icons/heatUp.png", backgroundColor: "#ffffff"
		}
		standardTile("heatLevelDown", "device.heatingSetpoint", width: 3, height: 1, canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
			state "heatLevelDown", label:'Heat', action:"heatLevelDown", icon:"http://raw.githubusercontent.com/yracine/device-type.myecobee/master/icons/heatDown.png", backgroundColor: "#ffffff"

		}
		valueTile("heatingSetpoint", "device.heatingSetpointDisplay", width: 3, height: 2, inactiveLabel: false) {
			state ("heat", label:'${currentValue}', unit:"F",
			backgroundColors:[
				[value: 0, color: "#153591"],
				[value: 7, color: "#1e9cbb"],
				[value: 15, color: "#90d2a7"],
				[value: 23, color: "#44b621"],
				[value: 29, color: "#f1d801"],
				[value: 35, color: "#d04e00"],
				[value: 37, color: "#bc2323"],
				// Fahrenheit Color Range
				[value: 31, color: "#153591"],
				[value: 44, color: "#1e9cbb"],
				[value: 59, color: "#90d2a7"],
				[value: 74, color: "#44b621"],
				[value: 84, color: "#f1d801"],
				[value: 95, color: "#d04e00"],
				[value: 96, color: "#bc2323"]
			])
		}
		valueTile("coolingSetpoint", "device.coolingSetpointDisplay", width: 3, height: 2, inactiveLabel: false,decoration: "flat") {
			state ("cool", label:'${currentValue}', unit:"F",
			backgroundColors:[
				[value: 0, color: "#153591"],
				[value: 7, color: "#1e9cbb"],
				[value: 15, color: "#90d2a7"],
				[value: 23, color: "#44b621"],
				[value: 29, color: "#f1d801"],
				[value: 35, color: "#d04e00"],
				[value: 37, color: "#bc2323"],
				// Fahrenheit Color Range
				[value: 31, color: "#153591"],
				[value: 44, color: "#1e9cbb"],
				[value: 59, color: "#90d2a7"],
				[value: 74, color: "#44b621"],
				[value: 84, color: "#f1d801"],
				[value: 95, color: "#d04e00"],
				[value: 96, color: "#bc2323"]
			])
			            

		}

		standardTile("coolLevelUp", "device.coolingSetpoint", width: 3, height: 1, canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
			state "coolLevelUp", label:'Cool', action:"coolLevelUp", icon:"http://raw.githubusercontent.com/yracine/device-type.myecobee/master/icons/coolUp.png",  backgroundColor: "#ffffff"
		}

		standardTile("coolLevelDown", "device.coolingSetpoint", width: 3, height: 1, canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
			state "coolLevelDown", label:'Cool', action:"coolLevelDown", icon:"http://raw.githubusercontent.com/yracine/device-type.myecobee/master/icons/coolDown.png",backgroundColor: "#ffffff"
		}

 
		valueTile("heatingSetpoint", "device.heatingSetpointDisplay", width: 3, height: 2, inactiveLabel: false) {
			state "heat", label:'${currentValue}', unit:"F",
			backgroundColors: getBackgroundColors()
		}
		valueTile("coolingSetpoint", "device.coolingSetpointDisplay", width: 3, height: 2, inactiveLabel: false,decoration: "flat") {
			state "cool", label:'${currentValue}', unit:"F",
			backgroundColors: getBackgroundColors()
		}
        
		controlTile("coolSliderControl", "device.coolingSetpoint", "slider", height: 1, width:6, inactiveLabel: false) {
			state "setCoolingSetpoint", action:"thermostat.setCoolingSetpoint", backgroundColor: "#1e9cbb"
		}
		controlTile("heatSliderControl", "device.heatingSetpoint", "slider", height: 1, width: 6, inactiveLabel: false) {
			state "setHeatingSetpoint", action:"thermostat.setHeatingSetpoint", backgroundColor:"#d04e00"
		}
*/        
        
		standardTile("heatLevelDown", "device.heatingSetpoint", width: 2, height: 2, canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
			state "heatLevelDown", label:'Heat', action:"heatLevelDown", icon: "${getCustomImagePath()}heatDown.png", backgroundColor: "#ffffff"

		}
		standardTile("heatLevelUp", "device.heatingSetpoint", width: 2, height: 2, canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
			state "heatLevelUp", label:'Heat', action:"heatLevelUp", icon: "${getCustomImagePath()}heatUp.png", backgroundColor: "#ffffff"
		}

		standardTile("heatingSetpoint", "device.heatingSetpointDisplay", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "heat", label:'${currentValue} heat', unit:"F", backgroundColor:"#d04e00"
		}
		standardTile("coolLevelDown", "device.coolingSetpoint", width: 2, height: 2, canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
			state "coolLevelDown", label:'Cool', action:"coolLevelDown", icon: "${getCustomImagePath()}coolDown.png",backgroundColor: "#ffffff"
		}

		standardTile("coolLevelUp", "device.coolingSetpoint", width: 2, height: 2, canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
			state "coolLevelUp", label:'Cool', action:"coolLevelUp", icon: "${getCustomImagePath()}coolUp.png",  backgroundColor: "#ffffff"
		}

		standardTile("coolingSetpoint", "device.coolingSetpointDisplay", height: 2, width: 2,inactiveLabel: false, decoration: "flat") {
			state "cool", label:'${currentValue} cool', unit:"F", backgroundColor:"#153591"
		}
        

		standardTile("mode", "device.thermostatMode", inactiveLabel: false,
			decoration: "flat", width: 2, height: 2) {
			state "off", label: '${name}',action: "switchMode", nextState: "updating",
				icon: "${getCustomImagePath()}nest-leaf-icon.png", backgroundColor: "#ffffff"
			state "cool", label: '${name}', action: "switchMode", nextState: "updating",
				icon: "${getCustomImagePath()}coolMode.png", backgroundColor: "#ffffff"
			state "heat", label: '${name}', action: "switchMode", nextState: "updating",
				icon: "${getCustomImagePath()}heatMode.png", backgroundColor: "#ffffff"
			state "auto", label: '${name}', action: "switchMode", nextState: "updating",
				icon: "${getCustomImagePath()}autoMode.png", backgroundColor: "#ffffff"
			state "updating",label:"Working",icon: "st.motion.motion.inactive"		                
		}
               
		standardTile("fanMode", "device.thermostatFanMode", inactiveLabel: false,
			decoration: "flat", width: 2, height: 2) {
			state "auto", label: '${name}', action: "thermostat.fanOn", 
				icon: "${getCustomImagePath()}Fan.png", backgroundColor: "#ffffff",
				nextState: "updating"
			state "on", label: '${name}', action: "thermostat.fanAuto", 
				icon: "${getCustomImagePath()}Fan.png",backgroundColor: "#ffffff",
				nextState: "updating"
			state "updating",label:"Working",icon: "${getCustomImagePath()}Working-icon.png"		
		}
		standardTile("operatingState", "device.thermostatOperatingState", width: 2, height: 2) {
			state "idle", label:'${name}', backgroundColor:"#ffffff"
			state "heating", label:'${name}', backgroundColor:"#e86d13"
			state "cooling", label:'${name}', backgroundColor:"#00A0DC"
		}
		standardTile("switchProgram", "device.programNameForUI",  canChangeIcon: false,
			inactiveLabel: false, width: 2, height: 2, decoration: "flat") {
			state "Home", label: 'Set:${name}', action: "asleep", 
				icon: "${getCustomImagePath()}home4.png",backgroundColor: "#ffffff",
				nextState: "updating"
			state "Sleep", label: 'Set:${name}', action: "away", 
 				icon: "${getCustomImagePath()}sleep.png",backgroundColor: "#ffffff",
				nextState: "updating"
			state "Away", label: 'Set:${name}', action: "home", 
				icon: "${getCustomImagePath()}Away.png",backgroundColor: "#ffffff",
				nextState: "updating"
			state "Other", label: 'Set:${name}', action: "resumeThisTstat", 
				icon: "${getCustomImagePath()}Clock.png",backgroundColor: "#ffffff"
 			state "updating",label:"Working",icon: "${getCustomImagePath()}Working-icon.png"		
	                 
		}
		valueTile("equipStatus", "device.equipmentStatus", inactiveLabel: false,
			width: 6, height: 2) {
			state "default", label: 'EquipmentStatus ${currentValue}',
 			backgroundColor: "#ffffff"
		}
		valueTile("programEndTimeMsg", "device.programEndTimeMsg", inactiveLabel: false,	
			width: 2, height: 2) {
			state "default", label: '${currentValue}'
		}
		valueTile("alerts", "device.alerts", inactiveLabel: false,
			width: 6, height: 2) {
			state "default", label: 'Alerts ${currentValue}',
 			backgroundColor: "#ffffff"
		}
        
		standardTile("fanMinOnTime", "device.fanMinOnTime", inactiveLabel: false,
			decoration: "flat", width: 2, height: 2, canChangeIcon: false,) {
			state "default", label: 'FanMinTime ${currentValue}',
			icon: "${getCustomImagePath()}Fan.png"
		}
		// Program Tiles
		standardTile("programScheduleName", "device.programScheduleName", inactiveLabel: false, 
			width: 2, height: 2, decoration: "flat",  canChangeIcon: false) {
			state "default", label: 'Mode ${currentValue}',
			icon: "${getCustomImagePath()}office7.png",
			backgroundColor: "#ffffff"
		}
		standardTile("programType", "device.programType", inactiveLabel: false, 
			width: 2,height: 2, decoration: "flat",  canChangeIcon: false) {
			state "default", label: 'ProgType ${currentValue}', 
			icon: "${getCustomImagePath()}office7.png"
		}
		standardTile("programCoolTemp", "device.programCoolTempDisplay", 
			width: 2, height: 2, decoration: "flat",inactiveLabel: false, canChangeIcon: false) {
			state "default", label: 'ProgCool ${currentValue}',
			icon: "${getCustomImagePath()}office7.png"
		}
		standardTile("programHeatTemp", "device.programHeatTempDisplay", 
			width: 2, height: 2, decoration: "flat",inactiveLabel: false,  canChangeIcon: false) {
			state "default", label: 'ProgHeat ${currentValue}', 			
			icon: "${getCustomImagePath()}office7.png"
		}
		standardTile("resProgram", "device.thermostatMode", inactiveLabel: false,
			decoration: "flat",width: 2, height: 2, canChangeIcon: false) {
			state "default", label: 'ResumeProgram', action: "resumeThisTstat", 
			icon: "${getCustomImagePath()}office7.png"
		}
		// Weather Tiles
		standardTile("weatherIcon", "device.weatherSymbol", inactiveLabel: false, width: 2, height: 2,
			decoration: "flat",  canChangeIcon: false) {
			state "-2",			label: 'updating...',	icon: "st.unknown.unknown.unknown"
			state "0",			label: 'Sunny',			icon: "st.Weather.weather14"
			state "1",			label: 'FewClouds',		icon: "st.Weather.weather15"
			state "2",			label: 'PartlyCloudy',	icon: "st.Weather.weather15"
			state "3",			label: 'MostlyCloudy',	icon: "st.Weather.weather15"
			state "4",			label: 'Overcast',		icon: "st.Weather.weather13"
			state "5",			label: 'Drizzle',		icon: "st.Weather.weather9"
			state "6",			label: 'Rain',			icon: "st.Weather.weather10"
			state "7",			label: 'FreezingRain',	icon: "st.Weather.weather10"
			state "8",			label: 'Showers',		icon: "st.Weather.weather10"
			state "9",			label: 'Hail',			icon: "st.custom.wuk.sleet"
			state "10",			label: 'Snow',			icon: "st.Weather.weather6"
			state "11",			label: 'Flurries',		icon: "st.Weather.weather6"
			state "12",			label: 'Sleet',			icon: "st.Weather.weather6"
			state "13",			label: 'Blizzard',		icon: "st.Weather.weather7"
			state "14",			label: 'Pellets',		icon: "st.custom.wuk.sleet"
			state "15",			label: 'ThunderStorms',	icon: "st.custom.wuk.tstorms"
			state "16",			label: 'Windy',			icon: "st.Transportation.transportation5"
			state "17",			label: 'Tornado',		icon: "st.Weather.weather1"
			state "18",			label: 'Fog',			icon: "st.Weather.weather13"
			state "19",			label: 'Hazy',			icon: "st.Weather.weather13"
			state "20",			label: 'Smoke',			icon: "st.Weather.weather13"
			state "21",			label: 'Dust',			icon: "st.Weather.weather13",
 			backgroundColor: "#ffffff"
            
		}
		valueTile("weatherDateTime", "device.weatherDateTime", inactiveLabel: false,
			width: 3, height: 2, decoration: "flat", canChangeIcon: false) {
			state "default", label: '${currentValue}', 
			icon: "${getCustomImagePath()}weather11.png",
 			backgroundColor: "#ffffff"
		}
		valueTile("weatherConditions", "device.weatherCondition", 
			inactiveLabel: false, width: 3, height: 2, decoration: "flat", canChangeIcon: false) {
			state "default", label: 'Forecast ${currentValue}',
			icon: "${getCustomImagePath()}weather11.png",
 			backgroundColor: "#ffffff"
		}
		standardTile("weatherTemperature", "device.weatherTemperatureDisplay", inactiveLabel:false, width: 2, height: 2, 
			decoration: "flat", canChangeIcon: false) {
			state "default", label: 'OutdoorTemp ${currentValue}', unit: "C",
			icon: "${getCustomImagePath()}weather2.png",
			backgroundColor: "#ffffff"
		}
		standardTile("weatherRelativeHumidity", "device.weatherRelativeHumidity",
			inactiveLabel: false, width: 2, height: 2,decoration: "flat") {
			state "default", label: 'OutdoorHum ${currentValue}%', unit: "humidity",
			icon: "${getCustomImagePath()}weather2.png",
 			backgroundColor: "#ffffff"
		}
		valueTile("weatherTempHigh", "device.weatherTempHigh", inactiveLabel: false,
			width: 2, height: 2, decoration: "flat") {
			state "default", label: 'ForecastHigh ${currentValue}', unit: "C",
 			backgroundColor: "#ffffff"
		}
		valueTile("weatherTempLow", "device.weatherTempLow", inactiveLabel: false,
			width: 2, height: 2, decoration: "flat") {
			state "default", label: 'ForecastLow ${currentValue}', unit: "C",
 			backgroundColor: "#ffffff"
		}
		valueTile("weatherPressure", "device.weatherPressure", inactiveLabel: false,
			width: 2, height: 2, decoration: "flat") {
			state "default", label: 'Pressure ${currentValue}', unit: "hpa",
 			backgroundColor: "#ffffff"
		}
		valueTile("weatherWindDirection", "device.weatherWindDirection",
			inactiveLabel: false, width: 2, height: 2, decoration: "flat") {
			state "default", label: 'WindDirections ${currentValue}',
 			backgroundColor: "#ffffff"
		}
		valueTile("weatherWindSpeed", "device.weatherWindSpeed", inactiveLabel: false,
			width: 2, height: 2, decoration: "flat") {
			state "default", label: 'WindSpeed ${currentValue}',
 			backgroundColor: "#ffffff"
		}
		standardTile("weatherPop", "device.weatherPop", inactiveLabel: false, 
 			width: 2,height: 2,  decoration: "flat", canChangeIcon: false) {
			state "default", label: 'RainProb. ${currentValue}%', unit: "%",
			icon: "${getCustomImagePath()}weather2.png",
 			backgroundColor: "#ffffff"
		}
		standardTile("refresh", "device.temperature", inactiveLabel: false, canChangeIcon: false,
			decoration: "flat",width: 2, height: 2) {
			state "default", label: 'Refresh',action: "refresh", icon:"${getCustomImagePath()}refresh.png", 			
			backgroundColor: "#ffffff"
		}
		standardTile("present", "device.presence", inactiveLabel: false, height:2, width:2, canChangeIcon: false) {
			state "not present", label:'${name}', backgroundColor: "#ffffff", icon:"st.presence.tile.presence-default" 
			state "present", label:'${name}', backgroundColor: "#ffffff", icon:"st.presence.tile.presence-default" 
		}
		htmlTile(name:"graphHTML", action: "getGraphHTML", width: 6, height: 8,  whitelist: ["www.gstatic.com"])
       
       
		main("thermostatMulti")
		details(["thermostatMulti",
			"name",	"mode",	"fanMode",
/*            
			"heatSliderControl",
			"heatingSetpoint",            
			"coolSliderControl",
			"coolingSetpoint",            
           
 			"heatLevelUp", "coolLevelUp", "heatingSetpoint", "coolingSetpoint", "heatLevelDown", "coolLevelDown",
*/
 			"heatingSetpoint", "heatLevelUp", "heatLevelDown",
 			"coolingSetpoint","coolLevelUp", "coolLevelDown",  
			"programType","programHeatTemp", "programCoolTemp",
			"switchProgram", "programScheduleName",
			"resProgram", "refresh",
			"programEndTimeMsg","fanMinOnTime", 
			"equipStatus",
 			"alerts",
			"weatherDateTime", "weatherConditions",
			"weatherTemperature", 
/*			 Commented out in new UI            
			"weatherIcon", "weatherTempHigh", "weatherTempLow", "weatherPressure",
			"weatherWindDirection", "weatherWindSpeed",
*/            
			"weatherRelativeHumidity","weatherPop",
//			"present",            
			"graphHTML"            
		])

	}
    
}


def getBackgroundColors() {
	def results
	if (state?.scale =='C') {
				// Celsius Color Range
		results=
			[        
				[value: 0, color: "#153591"],
				[value: 7, color: "#1e9cbb"],
				[value: 15, color: "#90d2a7"],
				[value: 23, color: "#44b621"],
				[value: 29, color: "#f1d801"],
				[value: 35, color: "#d04e00"],
				[value: 37, color: "#bc2323"]
			]
	} else {
		results =
				// Fahrenheit Color Range
			[        
				[value: 31, color: "#153591"],
				[value: 44, color: "#1e9cbb"],
				[value: 59, color: "#90d2a7"],
				[value: 74, color: "#44b621"],
				[value: 84, color: "#f1d801"],
				[value: 95, color: "#d04e00"],
				[value: 96, color: "#bc2323"]
			]  
	}
	return results    
}

mappings {
	path("/getGraphHTML") {action: [GET: "getGraphHTML"]}
}




void installed() {
/*    
	def HEALTH_TIMEOUT= (60 * 60)
	sendEvent(name: "checkInterval", value: HEALTH_TIMEOUT, data: [protocol: "cloud", displayed:(settings.trace?:false)])
	sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: JsonOutput.toJson([protocol: "cloud", scheme:"untracked"]), displayed: false)
*/    
	state?.scale=getTemperatureScale() 
	if (settings.trace) { 
		log.debug("installed>$device.displayName installed with settings: ${settings.inspect()} and state variables= ${state.inspect()}")
	}
}  

/* Ping is used by Device-Watch in attempt to reach the device
*/
def ping() {
	poll()
}

def updated() {
/*    
	def HEALTH_TIMEOUT= (60 * 60)
	sendEvent(name: "checkInterval", value: HEALTH_TIMEOUT, data: [protocol: "cloud", displayed:(settings.trace?:false)])
	sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: JsonOutput.toJson([protocol: "cloud", scheme:"untracked"]), displayed: false)
*/
	state?.retriesRefreshCounter=0    
	state?.scale=getTemperatureScale()
	data?.thermostatList=null    
	traceEvent(settings.logFilter,"updated>$device.displayName updated with settings: ${settings.inspect()} and state variables= ${state.inspect()}", settings.trace)
}

//remove from the selected devices list in Service Manager
void uninstalled() {
	traceEvent(settings.logFilter, "executing uninstalled for ${this.device.displayName}", settings.trace)
	parent.purgeChildDevice(this)    
}

private def evaluate(temp, heatingSetpoint, coolingSetpoint, nosetpoint=false) {
	def oldState = device.currentValue("thermostatOperatingState")
	traceEvent(settings.logFilter,"evaluate($temp, $heatingSetpoint, $coolingSetpoint), old State=$oldState", settings.trace)
	def threshold = (state?.scale == 'C') ? 0.5 : 1
	def mode = device.currentValue("thermostatMode")
	def heating = false
	def cooling = false
	def idle = true
 	def dataEvents=[:]
    
	if (mode in ["heat","emergency heat","auto"]) {
		if ((temp - heatingSetpoint) >= threshold) {
			heating = true
			if (!nosetpoint) {            
				setHeatingSetpoint(temp)
				traceEvent(settings.logFilter,"increasing heat to $temp", settings.trace)
			}                
			dataEvents= ['thermostatOperatingState':"heating"] 
		}
		else if (((heatingSetpoint - temp) >= threshold)) {
			if (!nosetpoint) {            
				setHeatingSetpoint(temp)
				traceEvent(settings.logFilter,"decreasing heat to $temp", settings.trace)
			}	        
		}
	}
	if (mode in ["cool","auto"]) {
		if ((coolingSetpoint - temp) >= threshold) {
			cooling = true
			if (!nosetpoint) {            
				setCoolingSetpoint(temp)
				traceEvent(settings.logFilter,"increasing cool to $temp", settings.trace)
			}	        
			dataEvents= ['thermostatOperatingState':"cooling"] 
		} else if (((temp - coolingSetpoint) >= threshold) && (!heating)) {
			if (!nosetpoint) {            
				setCoolingSetpoint(temp)
				traceEvent(settings.logFilter,"decreasing cool to $temp", settings.trace)
			}	        
		}
	}
	if (idle && !heating && !cooling) {
			dataEvents=  ['thermostatOperatingState':"idle"] 
				traceEvent(settings.logFilter,"idle state", settings.trace)
            
	}
   
	if (mode in ["auto"]) {
			dataEvents=  ['thermostatOperatingState':"Automatic at $coolingSetpoint,$heatingSetpoint"]  
	}            
	generateEvent(dataEvents)
	
}

void coolLevelUp() {
	def scale = state?.scale
	def coolingSetpointRangeHigh
	double nextLevel

	try  {   
		coolingSetpointRangeHigh=device.currentValue("coolingSetpointRangeHigh")
	} catch (e) {
		traceEvent(settings.logFilter,"coolLevelUp>$e, not able to get coolingSetpointRangeHigh ($coolingSetpointRangeHigh), using default value",
			true, GLOBAL_LOG_INFO, true)        
	}    
	coolingSetpointRangeHigh= (coolingSetpointRangeHigh) ? coolingSetpointRangeHigh.toDouble() : (scale == 'C')?30:99   
	def coolingSetpoint = device.currentValue("coolingSetpoint")
	nextLevel = (coolingSetpoint) ? coolingSetpoint.toDouble():(scale == 'C') ? 20.0 : 72     
	if (scale == 'C') {
		nextLevel = (nextLevel + 0.5).round(1)        
		traceEvent(settings.logFilter, "coolLevelUp>coolingSetpoint =$coolingSetpoint, nextLevel=$nextLevel", settings.trace)
		if (nextLevel > coolingSetpointRangeHigh.toDouble().round(0)) {
			nextLevel = coolingSetpointRangeHigh.toDouble().round(0)
		}
		setCoolingSetpoint(nextLevel)
	} else {
		nextLevel = nextLevel + 1    
		traceEvent(settings.logFilter, "coolLevelUp>coolingSetpoint =$coolingSetpoint, nextLevel=$nextLevel", settings.trace)
		if (nextLevel > coolingSetpointRangeHigh.toDouble()) {
			nextLevel = coolingSetpointRangeHigh.toDouble()
		}
		setCoolingSetpoint(nextLevel.intValue())
	}
	sendEvent(name:"programScheduleName",value:"hold", isStateChange:true, displayed:false)    
}

void coolLevelDown() {
	def scale = state?.scale
	def coolingSetpointRangeLow 
	double nextLevel

	try  {   
		coolingSetpointRangeLow=device.currentValue("coolingSetpointRangeLow")
	} catch (e) {
		traceEvent(settings.logFilter,"coolLevelDown>$e, not able to get coolingSetpointRangeLow ($coolingSetpointRangeLow), using default value",
			true, GLOBAL_LOG_INFO, true)        
	}    
	coolingSetpointRangeLow= (coolingSetpointRangeLow!=null)?coolingSetpointRangeLow.toDouble(): (scale == 'C')?10:50   
 
	def coolingSetpoint = device.currentValue("coolingSetpoint")
	nextLevel = (coolingSetpoint) ? coolingSetpoint.toDouble():(scale == 'C') ? 20.0 : 72     
	if (scale == 'C') {
		nextLevel = (nextLevel - 0.5).round(1)        
		traceEvent(settings.logFilter, "coolLevelDown>coolingSetpoint =$coolingSetpoint, nextLevel=$nextLevel", settings.trace)
		if (nextLevel < coolingSetpointRangeLow.toDouble().round(0)) {
			nextLevel = coolingSetpointRangeLow.toDouble().round(0)
		}
		setCoolingSetpoint(nextLevel)
	} else {
		nextLevel = (nextLevel - 1)
		traceEvent(settings.logFilter, "coolLevelDown>coolingSetpoint =$coolingSetpoint, nextLevel=$nextLevel", settings.trace)
		if (nextLevel < coolingSetpointRangeLow.toDouble()) {
			nextLevel = coolingSetpointRangeLow.toDouble()
		}
		setCoolingSetpoint(nextLevel.intValue())
	}
	sendEvent(name:"programScheduleName",value:"hold", isStateChange:true, displayed:false)    
}
void heatLevelUp() {
	def scale = state?.scale
	def heatingSetpointRangeHigh
	double nextLevel
    
	try {
		heatingSetpointRangeHigh = device.currentValue("heatingSetpointRangeHigh")
	} catch (e) {
		traceEvent(settings.logFilter, "heatLevelUp>$e, not able to get heatingSetpointRangeHigh ($heatingSetpointRangeHigh),using default value", 
			settings.trace, GLOBAL_LOG_WARN)
	}
	heatingSetpointRangeHigh = (heatingSetpointRangeHigh) ?heatingSetpointRangeHigh.toDouble(): (scale == 'C') ? 10.0 : 50
	traceEvent(settings.logFilter, "heatLevelUp>heatingSetpointRangeHigh =$heatingSetpointRangeHigh", settings.trace)
 
	def heatingSetpoint = device.currentValue("heatingSetpoint")
	nextLevel = (heatingSetpoint) ? heatingSetpoint.toDouble():(scale == 'C') ? 20.0 : 72     
	if (scale == 'C') {
		nextLevel = (nextLevel + 0.5).round(1)
		traceEvent(settings.logFilter, "heatLevelUp>heatingSetpoint =$heatingSetpoint, nextLevel=$nextLevel", settings.trace)
		if (nextLevel > heatingSetpointRangeHigh.toDouble().round(1)) {
			nextLevel = heatingSetpointRangeHigh.toDouble().round(1)
		}
		setHeatingSetpoint(nextLevel)
	} else {
		nextLevel = (nextLevel + 1)
		traceEvent(settings.logFilter, "heatLevelUp>heatingSetpoint =$heatingSetpoint, nextLevel=$nextLevel", settings.trace)
		if (nextLevel > heatingSetpointRangeHigh.toDouble()) {
			nextLevel = heatingSetpointRangeHigh.toDouble()
		}
		setHeatingSetpoint(nextLevel.intValue())
	}
	sendEvent(name:"programScheduleName",value:"hold", isStateChange:true, displayed:false)    
}
void heatLevelDown() {
	def scale = state?.scale
	def heatingSetpointRangeLow
	double nextLevel

	try {
		heatingSetpointRangeLow = device.currentValue("heatingSetpointRangeLow")
	} catch (e) {
		traceEvent(settings.logFilter,"heatLevelDown>$e, not able to get heatingSetpointRangeLow ($heatingSetpointRangeLow),using default value",
			settings.trace, GLOBAL_LOG_WARN)
	}
	heatingSetpointRangeLow = (heatingSetpointRangeLow!=null) ?heatingSetpointRangeLow.toDouble(): (scale == 'C') ? 10.0 : 50
	traceEvent(settings.logFilter, "heatLevelDown>heatingSetpointRangeLow =$heatingSetpointRangeLow", settings.trace)
	def heatingSetpoint = device.currentValue("heatingSetpoint")
	nextLevel = (heatingSetpoint) ? heatingSetpoint.toDouble():(scale == 'C') ? 20.0 : 72     
	if (scale == 'C') {
		nextLevel = (nextLevel - 0.5).round(1)
		traceEvent(settings.logFilter, "heatLevelDown>heatingSetpoint =$heatingSetpoint, nextLevel=$nextLevel", settings.trace)
		if (nextLevel <= heatingSetpointRangeLow.toDouble().round(1)) {
			nextLevel = heatingSetpointRangeLow.toDouble().round(1)
		}
		setHeatingSetpoint(nextLevel)
	} else {
		nextLevel = (nextLevel - 1)
		traceEvent(settings.logFilter, "heatLevelDown>heatingSetpoint =$heatingSetpoint, nextLevel=$nextLevel", settings.trace)
		if (nextLevel < heatingSetpointRangeLow.toDouble()) {
			nextLevel = heatingSetpointRangeLow.toDouble()
		}
		setHeatingSetpoint(nextLevel.intValue())
	}
	sendEvent(name:"programScheduleName",value:"hold", isStateChange:true, displayed:false)    
}

// handle commands


void setHeatingSetpoint(temp) {
	def scale = state?.scale
	def heatingSetpointRangeLow,heatingSetpointRangeHigh

	try  {   
		heatingSetpointRangeLow= device.currentValue("heatingSetpointRangeLow")
		heatingSetpointRangeHigh=device.currentValue("heatingSetpointRangeHigh")
	} catch (e) {
		traceEvent(settings.logFilter,"setHeatingSetpoint>$e, not able to get heatingSetpointRangeLow ($heatingSetpointRangeLow) or heatingSetpointRangeHigh ($heatingSetpointRangeHigh), using default values",
			true, GLOBAL_LOG_INFO, true)        
	}    
	heatingSetpointRangeLow= (heatingSetpointRangeLow)?: (scale == 'C')?10.0:50       
	heatingSetpointRangeHigh= (heatingSetpointRangeHigh)?: (scale == 'C')?30.0:99  
	
	if ((temp < heatingSetpointRangeLow.toDouble()) || (temp > heatingSetpointRangeHigh.toDouble())) {
		traceEvent(settings.logFilter,"setHeatingSetpoint>$temp is not within range [$heatingSetpointRangeLow...$heatingSetpointRangeHigh]",
			true, GLOBAL_LOG_WARN, true)        
	}    
	double tempValue    
    
	setHold("", device.currentValue("coolingSetpoint"), temp,
		null, null)
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'heatingSetpoint', value: temp,unit: scale,isStateChange:true)
	sendEvent(name: 'heatingSetpointDisplay', value: temp,unit: scale,isStateChange:true)
	def currentMode = device.currentValue("thermostatMode")
	String thermostatSetpointString
	if (currentMode=='heat')  {
		if (scale == "C") {
			tempValue = temp.toDouble().round(1)
			thermostatSetpointString = String.format('%2.1f', tempValue)
		} else {
			tempValue = temp.toDouble().round()
			thermostatSetpointString= String.format('%2d', tempValue.intValue())            
		}
		sendEvent(name:'thermostatSetpoint', value: thermostatSetpointString, unit: scale,isStateChange:true)     
	}
    
	if (currentMode=='auto') {  
		def currentCoolingSetpoint= device.currentValue("coolingSetpoint")
		double medianPoint= ((temp + currentCoolingSetpoint)/2).toDouble()
		if (scale == "C") {
			tempValue = medianPoint.round(1)
			thermostatSetpointString = String.format('%2.1f', medianPoint)
		} else {
			tempValue = medianPoint.toDouble().round()
			thermostatSetpointString= String.format('%2d', medianPoint.intValue())            
		}
		sendEvent(name:'thermostatSetpoint', value: thermostatSetpointString, unit: scale,isStateChange:true)     
       
  	}      
	    
        
}


void setCoolingSetpoint(temp) {
	def scale = state?.scale
	def coolingSetpointRangeLow,coolingSetpointRangeHigh
	try  {   
		coolingSetpointRangeLow= device.currentValue("coolingSetpointRangeLow")
		coolingSetpointRangeHigh=device.currentValue("coolingSetpointRangeHigh")
	} catch (e) {
		traceEvent(settings.logFilter,"setCoolingSetpoint>$e, not able to get coolingSetpointRangeLow ($coolingSetpointRangeLow) or coolingSetpointRangeHigh ($coolingSetpointRangeHigh), using default values",
			true, GLOBAL_LOG_INFO, true)        
	}    
	coolingSetpointRangeLow= (coolingSetpointRangeLow) ?: (scale == 'C')?10:50       
	coolingSetpointRangeHigh= (coolingSetpointRangeHigh) ?:  (scale == 'C')?30:99  
    
	if ((temp < coolingSetpointRangeLow.toDouble()) || (temp > coolingSetpointRangeHigh.toDouble())) {
		traceEvent(settings.logFilter,"setCoolingSetpoint>$temp is not within range [$coolingSetpointRangeLow...$coolingSetpointRangeHigh]",
			true, GLOBAL_LOG_WARN, true)        
	}    
	double tempValue    
	setHold("", temp, device.currentValue("heatingSetpoint"),
		null, null)
	def currentMode = device.currentValue("thermostatMode")
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'coolingSetpoint', value: temp, unit: scale, isStateChange:true)
	sendEvent(name: 'coolingSetpointDisplay', value: temp, unit: scale, isStateChange:true)
	String thermostatSetpointString
	if (currentMode=='cool') {
		if (scale == "C") {
			tempValue = temp.toDouble().round(1)
			thermostatSetpointString = String.format('%2.1f', tempValue)
		} else {
			tempValue = temp.toDouble().round()
			thermostatSetpointString= String.format('%2d', tempValue.intValue())            
		}
		sendEvent(name:'thermostatSetpoint', value: thermostatSetpointString, unit: scale,isStateChange:true)     
	}
	if (currentMode=='auto') {  
		def currentHeatingSetpoint= device.currentValue("heatingSetpoint")
	 	double medianPoint= ((temp + currentHeatingSetpoint)/2).toDouble()
		if (scale == "C") {
			tempValue = medianPoint.round(1)
			thermostatSetpointString = String.format('%2.1f', medianPoint)
		} else {
			tempValue = medianPoint.toDouble().round()
			thermostatSetpointString= String.format('%2d', medianPoint.intValue())            
		}
		sendEvent(name:'thermostatSetpoint', value: thermostatSetpointString, unit: scale,isStateChange:true)     
        
  	}      
    
}
void off() {
	setThermostatMode('off')
}
void auto() {
	setThermostatMode('auto')
}
void heat() {
	setThermostatMode('heat')
}
void emergencyHeat() {
	setThermostatMode('auxHeatOnly')
}
void auxHeatOnly() {
	setThermostatMode('auxHeatOnly')
}
void cool() {
	setThermostatMode('cool')
}
void fanOn() {
	setThermostatFanMode('on')
}
void fanAuto() {
	setThermostatFanMode('auto')
}
void fanOff() { // fanOff is not supported, setting it to 'auto' instead.
	setThermostatFanMode('auto')
}
def fanCirculate() {
	fanAuto()
	setFanMinOnTime(15)	// Set a minimum of 15 minutes of fan per hour
}

def getSupportedFanModes() {

	if (!state?.supportedThermostatFanModes) {	
		state?.supportedThermostatFanModes= (device.currentValue("supportedThermostatFanModes"))? 
			device.currentValue("supportedThermostatFanModes").toString().minus('[').minus(']').tokenize(',') : ['off','on','auto','circulate']
  
	}
    
	return state?.supportedThermostatFanModes
}    

def getSupportedThermostatModes() {

	if (!state?.supportedThermostatModes) {	
		state?.supportedThermostatModes = (device.currentValue("supportedThermostatModes")) ?
			device.currentValue("supportedThermostatModes").toString().minus('[').minus(']').tokenize(',') : ['off','heat', 'cool', 'auto']
	}
    
	return state?.supportedThermostatModes
}

def switchMode() {
	traceEvent(settings.logFilter, "swithMode>begin", settings.trace)
	def currentMode = device.currentState("thermostatMode")?.value
	def modeOrder =  getSupportedThermostatModes().minus('emergency heat').minus('auxHeatOnly')
	def index = modeOrder.indexOf(currentMode)
	def next = index >= 0 && index < modeOrder.size() - 1 ? modeOrder[index + 1] : modeOrder[0]
	traceEvent(settings.logFilter, "swithMode>switching thermostatMode to $next", settings.trace)
	"$next" ()
}

void setThermostatFanMode(mode) {
	mode=mode?.toLowerCase()
	mode = (mode == 'off') ? 'auto' : mode
	def supportedThermostatFanModes = getSupportedFanModes()
    
	if (supportedThermostatFanModes?.toString()?.contains(mode)) {
		setHold("", device.currentValue("coolingSetpoint"), 
			device.currentValue("heatingSetpoint"),mode, null)
	} else {
		traceEvent(settings.logFilter,"setThermostatFanMode>fan $mode mode is not supported in $supportedThermostatFanModes",
			true, GLOBAL_LOG_WARN, true)
		return            
	}    
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'thermostatFanMode', value: mode,isStateChange:true)
}
void setFanMinOnTime(minutes) {
	setThermostatSettings("", ['fanMinOnTime': "${minutes}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'fanMinOnTime', value: minutes,isStateChange:true)
}

void setThermostatMode(mode) {
	mode=mode?.toLowerCase()
	def supportedThermostatModes = getSupportedThermostatModes()
	mode = (mode == 'emergency heat') ? 'auxHeatOnly' : mode
    
	if (supportedThermostatModes?.toString()?.contains(mode)){
		setThermostatSettings("", ['hvacMode': "${mode}"])
        
	} else {   
		traceEvent(settings.logFilter,"setThermostatMode>cannot change tstat mode as $mode is not supported in $supportedThermostatModes",
			true, GLOBAL_LOG_WARN, true)
		return            
	}
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'thermostatMode', value: mode,isStateChange:true)
}
void ventilatorOn() {
	setVentilatorMode('on')
}
void ventilatorOff() {
	setVentilatorMode('off')
}
void ventilatorAuto() {
	setVentilatorMode('auto')
}
void setVentilatorMinOnTime(minutes) {
	setThermostatSettings("", ['vent': "minontime",
			'ventilatorMinOnTime': "${minutes}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'ventilatorMinOnTime', value: minutes)
	sendEvent(name: 'ventilatorMode', value: "minontime",isStateChange:true)
}
void setVentilatorMode(mode) {
	setThermostatSettings("", ['vent': "${mode}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'ventilatorMode', value: mode,isStateChange:true)
}
void setCondensationAvoid(flag) { // set the flag to true or false
	flag = flag == 'true' ? 'true' : 'false'
 	def mode = (flag=='true')? 'auto': 'manual'
	setHumidifierMode(mode)
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'condensationAvoid', value: flag,isStateChange:true)
}
void setDehumidifyWithAC(flag) { // set the flag to true or false
	flag = flag == 'true' ? 'true' : 'false'
	setThermostatSettings("", ['dehumidifyWithAC': "${flag}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'dehumidifyWithAC', value: flag,isStateChange:true)
}

// value is in Farenheit && must be a multiple of 5 between 0..50.
void setDehumidifyACOffset(value) { 
	if ((!value.isNumber()) || (value <0 || value > 50)) {
		traceEvent(settings.logFilter,"setDehumidifyACOffset>offset value must be between 0 and 50 and a multiple of 5 (in Farenheit)",
			true, GLOBAL_LOG_WARN, true)
		return            
	}    
	def scale = state?.scale
	setThermostatSettings("", ['dehumidifyOvercoolOffset': "${value}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'dehumidifyOvercoolOffset', value: value,isStateChange:true)
}
/* The minimum temperature difference between the heat and cool values (in Farenheit or Celsus depending on your scale*/


void setHeatCoolMinDelta(value) { //value is an integer between 3 and 50
	if ((!value.isNumber()) || (value <3 || value > 50)) {
		traceEvent(settings.logFilter,"setHeatCoolMinDelta>offset value must be between 3 and 50",
			true, GLOBAL_LOG_WARN, true)
		return            
	}    
	int valueDelta    
	def scale = state?.scale
	if (scale == 'C') {
		valueDelta = (value * 9/5 * 10) // need to send temperature in F multiply by 10
	} else {
		valueDelta = (value * 10) // need to send temperature in F multiply by 10
	}
	setThermostatSettings("", ['heatCoolMinDelta': "${valueDelta}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'heatCoolMinDelta', value: value,isStateChange:true, unit: scale)
}

void setCompressorProtectionMinTime(value) { //value is an integer between 200 and 900
	if ((!value.isNumber()) || (value <200 || value > 900)) {
		traceEvent(settings.logFilter,"setCompressorProtectionMinTime>Min time value in seconds must be between 200 and 900",
			true, GLOBAL_LOG_WARN, true)
		return            
	}    
	setThermostatSettings("", ['compressorProtectionMinTime': "${value}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'compressorProtectionMinTime', value: value,isStateChange:true)
}


void setCompressorProtectionMinTemp(value) { //value is an integer between -30 and +80
	if ((!value.isNumber()) || (value <-30 || value > 80)) {
		traceEvent(settings.logFilter,"setCompressorProtectionMinTemp>temp value must be between -30 and +80",
			true, GLOBAL_LOG_WARN, true)
		return            
	}    
	int valueDelta    
	def scale = state?.scale
	if (scale == 'C') {
		valueDelta =  cToF(value) *10 // need to send temperature in F multiply by 10
	} else {
		valueDelta = (value * 10) // need to send temperature in F multiply by 10
	}
	setThermostatSettings("", ['compressorProtectionMinTemp': "${valueDelta}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'compressorProtectionMinTemp', value: value,isStateChange:true, unit: scale)
}

void setAuxMaxOutdoorTemp(value) { //value is an integer between -30 and +80
	if ((!value.isNumber()) || (value <-30 || value > 80)) {
		traceEvent(settings.logFilter,"setAuxMaxOutdoorTemp>temp value must be between -30 and +80",
			true, GLOBAL_LOG_WARN, true)
		return            
	}    
	int valueDelta    
	def scale = state?.scale
	if (scale == 'C') {
		valueDelta =  cToF(value) *10 // need to send temperature in F multiply by 10
	} else {
		valueDelta = (value * 10) // need to send temperature in F multiply by 10
	}
	setThermostatSettings("", ['auxMaxOutdoorTemp': "${valueDelta}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'auxMaxOutdoorTemp', value: value,isStateChange:true, unit: scale)
}
void setStage1HeatDifferentialTemp(value) { //value is an integer between 0 and 5
	if ((!value.isNumber()) || (value <0 || value > 5)) {
		traceEvent(settings.logFilter,"setStage1HeatDiffTemp>offset value must be between 0 and 5",
			true, GLOBAL_LOG_WARN, true)
		return            
	}    
	int valueDelta    
	def scale = state?.scale
	if (scale == 'C') {
		valueDelta = (value * 9/5 * 10) // need to send temperature in F multiply by 10
	} else {
		valueDelta = (value * 10) // need to send temperature in F multiply by 10
	}
	setThermostatSettings("", ['stage1HeatingDifferentialTemp': "${valueDelta}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'stage1HeatingDifferentialTemp', value: value,isStateChange:true,unit: scale)
}

void setStage1CoolDifferentialTemp(value) { //value is an integer between 0 and 5
	if ((!value.isNumber()) || (value <0 || value > 5)) {
		traceEvent(settings.logFilter,"setStage1CoolDiffTemp>offset value must be between 0 and 5",
			true, GLOBAL_LOG_WARN, true)
		return            
	}    
	int valueDelta    
	def scale = state?.scale
	if (scale == 'C') {
		valueDelta = (value * 9/5 * 10) // need to send temperature in F multiply by 10
	} else {
		valueDelta = (value * 10) // need to send temperature in F multiply by 10
	}
	setThermostatSettings("", ['stage1CoolingDifferentialTemp': "${valueDelta}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'stage1CoolingDifferentialTemp', value: value,isStateChange:true, unit: scale)
}

void setHeatDissipationTime(value) { //value is an integer between 0 and 900
	if ((!value.isNumber()) || (value <0 || value > 900)) {
		traceEvent(settings.logFilter,"setHeatDissipationTime>dissipation value must be between 0 and 600",
			true, GLOBAL_LOG_WARN, true)
		return            
	}    
	setThermostatSettings("", ['stage1HeatingDissipationTime': "${value}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'stage1HeatingDissipationTime', value: value,isStateChange:true)
}

void setCoolDissipationTime(value) { //value is an integer between 0 and 900
	if ((!value.isNumber()) || (value <0 || value > 900)) {
		traceEvent(settings.logFilter,"setCoolDissipationTime>dissipation value must be between 0 and 600",
			true, GLOBAL_LOG_WARN, true)
		return            
	}    
	setThermostatSettings("", ['stage1CoolingDissipationTime': "${value}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'stage1CoolingDissipationTime', value: value,isStateChange:true)
}


void dehumidifierOn() {
	setDehumidifierMode('on')
}
void dehumidifierOff() {
	setDehumidifierMode('off')
}
void setDehumidifierMode(mode) {
	setThermostatSettings("", ['dehumidifierMode': "${mode}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'dehumidifierMode', value: mode,isStateChange:true)
}
void setDehumidifierLevel(level) {	
	setThermostatSettings("", ['dehumidifierLevel': "${level}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'dehumidifierLevel', value: level,isStateChange:true)
}
void humidifierAuto() {
	setHumidifierMode('auto')
}
void humidifierManual() {
	setHumidifierMode('manual')
}
void humidifierOff() {
	setHumidifierMode('off')
}
void setHumidifierMode(mode) {
	setThermostatSettings("", ['humidifierMode': "${mode}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'humidifierMode', value: mode,isStateChange:true)
}
void setHumidifierLevel(level) {		
	setThermostatSettings("", ['humidity': "${level}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'humidifierLevel', value: level,isStateChange:true)
}
// Only valid for ecobee3 & beyond thermostats, not for the older versions ( EMS, Smart, Smart-SI thermostats)
void followMeComfort(flag) {
	flag = flag == 'true' ? 'true' : 'false'
	setThermostatSettings("", ['followMeComfort': "${flag}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'followMeComfort', value: flag,isStateChange:true)
}
// Only valid for ecobee3 and beyond thermostats, not the older versions (EMS or Smart, Smart-SI thermostats)

void autoAway(flag) {
	flag = flag == 'true' ? 'true' : 'false'
	setThermostatSettings("", ['autoAway': "${flag}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'autoAway', value: flag,isStateChange:true)
}

void setBacklightOffTime (value) {

	if (!value.isNumber()) {
		traceEvent(settings.logFilter,"setBacklightOffTime>value $value is invalid, must be an integer value in seconds",settings.trace, GLOBAL_LOG_ERROR,true)
		return    
	}    
	setThermostatSettings("", ['backlightOffTime': "${value}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'backlightOffTime', value: value,isStateChange:true)
}

void setBacklightSleepIntensity (value) {
	if ((!value.isNumber())  || (value <0 || value>10)) {
		traceEvent(settings.logFilter,"setBacklightSleepIntensity>value $value is invalid, must be an integer between 0 and 10",settings.trace, GLOBAL_LOG_ERROR,true)
		return    
	}    
	setThermostatSettings("", ['backlightSleepIntensity': "${value}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'backlightSleepIntensity', value: value,isStateChange:true)
}

void setBacklightOnIntensity (value) {
	if ((!value.isNumber())  || (value <0 || value>10)) {
		traceEvent(settings.logFilter,"setBacklightOnIntensity>value $value is invalid, must be an integer between 0 and 10",settings.trace,GLOBAL_LOG_ERROR, true)
		return    
	}    
	setThermostatSettings("", ['backlightOnIntensity': "${value}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	sendEvent(name: 'backlightOnIntensity', value: value,isStateChange:true)
}

void awake() {
	setThisTstatClimate("Awake")    
}
void away() {
	setThisTstatClimate("Away")
}
void present() {
	home()
}
void home() {
	setThisTstatClimate("Home")

}
void asleep() {
	setThisTstatClimate("Sleep")
}
void quickSave() {
	def thermostatId= determine_tstat_id("") 	    
	def currentProgramType = device.currentValue("programType")
	if (currentProgramType.toUpperCase() == 'VACATION') {
		traceEvent(settings.logFilter,"quickSave>thermostatId = ${thermostatId},cannot do quickSave switch due to vacation settings",settings.trace)
		return
	}
	float quickSaveSetBack, quickSaveSetForw, quickSaveHeating, quickSaveCooling
	quickSaveCooling = device.currentValue("programCoolTemp")?.toFloat()
	quickSaveHeating = device.currentValue("programHeatTemp")?.toFloat()
	def scale = state?.scale
	if (scale == 'C') {
    
		quickSaveSetBack = (data.thermostatList[0]?.settings?.quickSaveSetBack.toFloat()  * 5/9 /10).round(1) // approximate conversion of differential to celcius
		quickSaveSetForw = (data.thermostatList[0]?.settings?.quickSaveSetForward.toFloat() * 5/9 /10).round(1)
		traceEvent(settings.logFilter,"quickSave>thermostatId = ${thermostatId},quickSaveSetForw = $quickSaveSetForw, quickSaveSetBack=$quickSaveSetBack",settings.trace)
		quickSaveCooling = (quickSaveCooling + quickSaveSetForw).round(1)
		quickSaveHeating = (quickSaveHeating - quickSaveSetBack).round(1)
	} else {
		quickSaveSetBack = data.thermostatList[0]?.settings?.quickSaveSetBack?.toFloat() 
		quickSaveSetForw = data.thermostatList[0]?.settings?.quickSaveSetForward?.toFloat() 
		quickSaveCooling = (quickSaveCooling + quickSaveSetForw).round(0)
		quickSaveHeating = (quickSaveHeating - quickSaveSetBack).round(0)
	}
	setHold(thermostatId, quickSaveCooling, quickSaveHeating, null, null)
	def quickSaveMap = [
		'programScheduleName': "Save",
		'programNameForUI': "Save"
	]        
	generateEvent(quickSaveMap)    
	refresh_thermostat(thermostatId)
	    
}

void setTargetHumidity(value) {	
	if (!value.isNumber()) {
    
		traceEvent(settings.logFilter,"setTargetHumidity>$value supplied is not a number, exiting...",
			true, GLOBAL_LOG_WARN, true) 
		return            
	}
	def hasDehumidifier = (device.currentValue("hasDehumidifier")) ? (device.currentValue("hasDehumidifier")) : 'false'
	def hasHumidifier = (device.currentValue("hasHumidifier")) ? (device.currentValue("hasHumidifier")) : 'false'
	def ecobeeHumidity = device.currentValue("humidity")
	def currentTargetHumidity = device.currentValue("targetHumidity") ?: 50
	def mode = device.currentValue("thermostatMode")
    
	if ((mode in ['heat', 'cool', 'auto', 'off']) && (hasDehumidifier =='true') && (ecobeeHumidity.toInteger() >= value)) {
		traceEvent (settings.logFilter,"setTargetHumidity>dehumidify the house with target dehumidify level=${value}",settings.trace)
		setThermostatSettings("", ['dehumidifierMode': 'on', 'dehumidifierLevel': "${value}"])
	} else if ((mode in ['auto', 'cool']) && (ecobeeHumidity.toInteger() >= value)) { 
		traceEvent (settings.logFilter,"setTargetHumidity>mode is $mode, dehumidify the house with AC as target dehumidify level=${value}",settings.trace)
		setThermostatSettings("", ['dehumidifyWithAC': 'true', 'dehumidifierLevel': "${value}"])
	} else if ((mode in ['heat', 'emergency heat', 'auto', 'off']) && (hasHumidifier == 'true') && (ecobeeHumidity.toInteger() <= value)) {
		traceEvent (settings.logFilter,"setTargetHumidity>humidify the house with target humidify level=${value}",settings.trace)
		setThermostatSettings("", ['humidifierMode': 'manual', 'humidity': "${value}"])
	} else {
		traceEvent (settings.logFilter,"setTargetHumidity>ecobeeHumidity reported at the tstat is $ecobeeHumidity and target is $value, no need to trigger humidifier or dehumidifier at this time",settings.trace)
		sendEvent(name: 'targetHumidity', value: currentTargetHumidity,isStateChange:true,unit: '%')
		return    
	}    
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck?.contains("done"))) {
		traceEvent(settings.logFilter,"setTargetHumidity>target_humidity ($value) was not set, exception $exceptionCheck",
			true, GLOBAL_LOG_WARN, true)        
		return		    
	} 

	sendEvent(name: 'targetHumidity', value: value,isStateChange:true,unit: '%')

	    
}

void setThermostatProgram(climateName) {
	setThisTstatClimate(climateName)
}
  
void resumeThermostatProgram(resumeFlag) {
	resume(resumeFlag)
}  
void setThisTstatClimate(climateName) {

	def thermostatId= determine_tstat_id("") 	    
	def currentProgram = device.currentValue("programScheduleName")
	def currentProgramType = device.currentValue("programType").trim().toUpperCase()
	if (currentProgramType == 'VACATION') {
		traceEvent(settings.logFilter,"setThisTstatClimate>thermostatId = ${settings.thermostatId},cannot do the prog switch due to vacation settings",settings.trace)
		return
	}
    
	resumeProgram()
	setClimate(thermostatId, climateName)
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
        
	sendEvent(name: 'programNameForUI', value: climateName, isStateChange: true)
	sendEvent(name: 'setClimate', value: climateName, isStateChange: true)
        
	refresh_thermostat(thermostatId) // to refresh the values in the UI
	if (climateName.toUpperCase().contains('AWAY')) { 
		sendEvent(name: "presence", value: "not present", isStateChange:true)
	} else {        
		sendEvent(name: "presence", value: "present",isStateChange:true)
	}    
}


void setHeatingSetpointRange(rangeArray=[]) {

	if ((!rangeArray) || (rangeArray.size()!=2)) {
		traceEvent(settings.logFilter,"setHeatingSetpointRange>cannot change the thermostat Range, value ($rangeArray) is null or invalid",settings.trace, GLOBAL_LOG_WARN,true)
		return    
	}    
	def temp_min=rangeArray[0]
	def temp_max=rangeArray[1]	
	setHeatingSetpointRangeLow(temp_min)    
	setHeatingSetpointRangeHigh(temp_max)    
}

void setCoolingSetpointRange(rangeArray=[]) {

	if ((!rangeArray) || (rangeArray.size()!=2)) {
		traceEvent(settings.logFilter,"setHeatingSetpointRange>cannot change the thermostat Range, value ($rangeArray) is null or invalid",settings.trace,GLOBAL_LOG_WARN,true)
		return    
	}    
	def temp_min=rangeArray[0]	
	def temp_max=rangeArray[1]	
	setCoolingSetpointRangeLow(temp_min)    
	setCoolingSetpointRangeHigh(temp_max)    
}


void setHeatingSetpointRangeLow(temp) {
	def scale = state?.scale
	double targetTemp    
	if (scale == 'C') {
		targetTemp = (cToF(temp) * 10) // need to send temperature in F multiply by 10
	} else {
		targetTemp = temp * 10
	}
	setThermostatSettings("", ['heatRangeLow': "${targetTemp.intValue()}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
    
	temp=temp.toFloat().round(1)    
	sendEvent(name: 'heatingSetpointRangeLow', value: temp, isStateChange:true,unit:scale)
	def setpointRangeHigh=device.currentValue('heatingSetpointRangeHigh')
//	String heatingSetpointRange= "$temp,$setpointRangeHigh"
	def heatingSetpointRange= [temp,setpointRangeHigh]
	sendEvent(name: "heatingSetpointRange", value: heatingSetpointRange, isStateChange: true, displayed: (settings.trace?:false),unit:scale)
}

void setHeatingSetpointRangeHigh(temp) {
	def scale = state?.scale
	double targetTemp    
	if (scale == 'C') {
		targetTemp = (cToF(temp) * 10) // need to send temperature in F multiply by 10
	} else {
		targetTemp = temp * 10
	}
	setThermostatSettings("", ['heatRangeHigh': "${targetTemp.intValue()}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
    
	temp=temp.toFloat().round(1)    
	sendEvent(name: 'heatingSetpointRangeHigh', value: temp, isStateChange:true,unit:scale)
	def setpointRangeLow=device.currentValue('heatingSetpointRangeLow')
//	String heatingSetpointRange= "$setpointRangeLow,$temp"
	def heatingSetpointRange= [setpointRangeLow,temp]
	sendEvent(name: "heatingSetpointRange", value: heatingSetpointRange, isStateChange: true, displayed: (settings.trace?:false),unit:scale)
}


void setCoolingSetpointRangeLow(temp) {
	def scale = state?.scale
	double targetTemp    
	if (scale == 'C') {
		targetTemp = (cToF(temp) * 10) // need to send temperature in F multiply by 10
	} else {
		targetTemp = temp * 10
	}
	setThermostatSettings("", ['coolRangeLow': "${targetTemp.intValue()}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
    
	temp=temp.toFloat().round(1)    
	sendEvent(name: 'coolingSetpointRangeLow', value: temp, isStateChange:true,unit:scale)
	def setpointRangeHigh=device.currentValue('coolingSetpointRangeHigh')
//	String coolingSetpointRange= "$temp,$setpointRangeHigh"
	def coolingSetpointRange= [temp,setpointRangeHigh]
	sendEvent(name: "coolingSetpointRange", value: coolingSetpointRange, isStateChange: true, displayed: (settings.trace?:false),unit:scale)
}

void setCoolingSetpointRangeHigh(temp) {
	def scale = state?.scale
	double targetTemp    
	if (scale == 'C') {
		targetTemp = (cToF(temp) * 10) // need to send temperature in F multiply by 10
	} else {
		targetTemp = temp * 10
	}
	setThermostatSettings("", ['coolRangeHigh': "${targetTemp.intValue()}"])
	def exceptionCheck=device.currentValue("verboseTrace")
	if ((exceptionCheck) && (!exceptionCheck.contains("done"))) {
		return    
	}    
	temp=temp.toFloat().round(1)    
	sendEvent(name: 'coolingSetpointRangeHigh', value: temp, isStateChange:true,unit:scale)
	def setpointRangeLow=device.currentValue('coolingSetpointRangeLow')
//	String coolingSetpointRange= "$setpointRangeLow,$temp"
	def coolingSetpointRange= [setpointRangeLow,temp]
	sendEvent(name: "coolingSetpointRange", value: coolingSetpointRange, isStateChange: true, displayed: (settings.trace?:false),unit:scale)
}


// parse events into attributes
def parse(String description) {

}

// thermostatId		single thermostatId (not a list)
// asyncValues		values from async poll
// RTeventFlag		Indicates whether or not RT events were sent by ecobee
private def refresh_thermostat(thermostatId, asyncValues=null, RTEventFlag=false) {
	settings.trace=true
    settings.logFilter=5    

	def scale = getTemperatureScale()
	state?.scale= scale    
	thermostatId=determine_tstat_id(thermostatId)
    
	if ((!asyncValues) && (!RTEventFlag)) {
		getThermostatInfo(thermostatId)
		String exceptionCheck = device.currentValue("verboseTrace")
		if ((exceptionCheck) && ((exceptionCheck.contains("exception")) || (exceptionCheck.contains("error")))) {
		// check if there is any exception or an error reported in the verboseTrace associated to the device 
			traceEvent(settings.logFilter, "poll>$exceptionCheck", true, GLOBAL_LOG_ERROR) 
			return    
		            
		}    
	}  else if (asyncValues) { 
		data.thermostatList=asyncValues.thermostatList		    
	}    
	def ecobeeType = determine_ecobee_type_or_location(tstatType)

	// determine if there is an event running
    
	Integer indiceEvent = 0    
	boolean foundEvent = false
	def foundEventClimateRef =null    
	if (data.thermostatList[0]?.events) {
		for (i in 0..data.thermostatList[0]?.events?.size() - 1) {
			if (data.thermostatList[0]?.events[i]?.running) {
				indiceEvent = i // save the right indice associated to the Event that is currently running
				foundEvent = true
				break
			}
		}
	}

	foundEventClimateRef= (foundEvent)? data.thermostatList[0]?.events[indiceEvent]?.holdClimateRef: null 								                
	def currentClimate = null
	def eventClimate = null
	// Get the current & event Climates 
	data.thermostatList[0]?.program?.climates.each() {
		if ((foundEvent) && (it.climateRef == foundEventClimateRef)) {
			eventClimate = it
		}
		if (it.climateRef == data.thermostatList[0]?.program?.currentClimateRef) {
			currentClimate = it
		}
	}

	def programScheduleName= (foundEventClimateRef)? eventClimate?.name + "_auto": 
		((foundEvent)? data.thermostatList[0]?.events[indiceEvent]?.name:currentClimate?.name)
	def programType= (foundEvent)?data.thermostatList[0]?.events[indiceEvent]?.type : currentClimate?.type

	def heatingSetpointRangeHigh= (data.thermostatList[0]?.settings?.heatRangeHigh)? data.thermostatList[0]?.settings?.heatRangeHigh.toInteger() : 990
	def heatingSetpointRangeLow= (data.thermostatList[0]?.settings?.heatRangeLow)? data.thermostatList[0]?.settings?.heatRangeLow.toInteger() : 500
	def coolingSetpointRangeHigh= (data.thermostatList[0]?.settings?.coolRangeHigh)? data.thermostatList[0]?.settings?.coolRangeHigh.toInteger() : 990
	def coolingSetpointRangeLow= (data.thermostatList[0]?.settings?.coolRangeLow)? data.thermostatList[0]?.settings?.coolRangeLow.toInteger() : 500
    
	traceEvent(settings.logFilter,"refresh_thermostat>heatingSetpointRangeHigh=$heatingSetpointRangeHigh",settings.trace)
	traceEvent(settings.logFilter,"refresh_thermostat>heatingSetpointRangeLow=$heatingSetpointRangeLow",settings.trace)
	traceEvent(settings.logFilter,"refresh_thermostat>coolingSetpointRangeHigh=$coolingSetpointRangeHigh",settings.trace)
	traceEvent(settings.logFilter,"refresh_thermostat>coolingSetpointRangeLow=$coolingSetpointRangeLow",settings.trace)
	float newHeatSP=(foundEvent)? getTemperatureValue(data.thermostatList[0]?.events[indiceEvent]?.heatHoldTemp):getTemperatureValue(data.thermostatList[0]?.runtime?.desiredHeat)
	float newCoolSP= (foundEvent)? getTemperatureValue(data.thermostatList[0]?.events[indiceEvent]?.coolHoldTemp):getTemperatureValue(data.thermostatList[0]?.runtime?.desiredCool)
	newHeatSP = newHeatSP.round(1)
	newCoolSP = newCoolSP.round(1)
	def currentHeatSP =device.currentValue("heatingSetpoint")
	def currentCoolSP = device.currentValue("coolingSetpoint") 
    
	currentHeatSP = (currentHeatSP!= null)? currentHeatSP.toFloat() : newHeatSP
	currentCoolSP = (currentCoolSP!= null)? currentCoolSP.toFloat() : newCoolSP
	traceEvent(settings.logFilter,"refresh_thermostat>currentHeatSP=$currentHeatSP,newHeatSP=$newHeatSP",settings.trace)
	traceEvent(settings.logFilter,"refresh_thermostat>currentCoolSP=$currentCoolSP,newCoolSP=$newCoolSP",settings.trace)

	String currentMode= data.thermostatList[0]?.settings?.hvacMode	
	def currentProgramScheduleName=(device.currentValue("programScheduleName"))?:programScheduleName    
	def dataEvents = [
		'programScheduleName':programScheduleName,    
		'programType': programType,
		'coolingSetpoint': (foundEvent)? data.thermostatList[0]?.events[indiceEvent]?.coolHoldTemp: 
			data.thermostatList[0]?.runtime?.desiredCool,
		'coolingSetpointDisplay': (foundEvent)? data.thermostatList[0]?.events[indiceEvent]?.coolHoldTemp: 
			data.thermostatList[0]?.runtime?.desiredCool,
		'heatingSetpoint': (foundEvent)? data.thermostatList[0]?.events[indiceEvent]?.heatHoldTemp: 
			data.thermostatList[0]?.runtime?.desiredHeat,
		'heatingSetpointDisplay': (foundEvent)? data.thermostatList[0]?.events[indiceEvent]?.heatHoldTemp: 
			data.thermostatList[0]?.runtime?.desiredHeat,
		'heatingSetpointRangeHigh': heatingSetpointRangeHigh,       
		'heatingSetpointRangeLow': heatingSetpointRangeLow,        
		'coolingSetpointRangeHigh': coolingSetpointRangeHigh,        
		'coolingSetpointRangeLow': coolingSetpointRangeLow,
		'heatStages':data.thermostatList[0]?.settings?.heatStages?.toString(),
		'coolStages':data.thermostatList[0]?.settings?.coolStages?.toString(),
		'hasBoiler': data.thermostatList[0]?.settings?.hasBoiler?.toString(),        
		'hasElectric': data.thermostatList[0]?.settings?.hasElectric?.toString(),        
		'hasHeatPump':data.thermostatList[0]?.settings?.hasHeatPump?.toString(),
		'hasForcedAir':data.thermostatList[0]?.settings?.hasForcedAir?.toString(),
		'modelNumber': data.thermostatList[0]?.modelNumber
	]    
	generateEvent(dataEvents)
	if (foundEvent && currentMode =='cool' && (programScheduleName in ['hold', 'auto'] && programScheduleName==currentProgramScheduleName) && 
		(newCoolSP && currentCoolSP != newCoolSP)) { // force the hold or auto event when in 'cool' mode and coolSP has changed manually
		sendEvent(name:"programScheduleName", value: programScheduleName, displayed:((settings.trace) ?:false), isStateChange:true)
		traceEvent(settings.logFilter,"refresh_thermostat>hold detected, currentCoolSP=$currentCoolSP,newCoolSP=$newCoolSP",settings.trace)
	}	
	if (foundEvent && currentMode == 'heat' && (programScheduleName in ['hold', 'auto'] && programScheduleName==currentProgramScheduleName) && 
		(newHeatSP && currentHeatSP != newHeatSP )) { // force the hold or auto event when in 'heat' mode and heatSP has changed manually
		sendEvent(name:"programScheduleName", value: programScheduleName, displayed:((settings.trace) ?:false), isStateChange:true)
		traceEvent(settings.logFilter,"refresh_thermostat>hold detected, currentHeatSP=$currentHeatSP,newHeatSP=$newHeatSP",settings.trace)
	}	
	if (foundEvent && currentMode == 'auto' && (programScheduleName in ['hold', 'auto'] && programScheduleName==currentProgramScheduleName) && 
		((newHeatSP && currentHeatSP != newHeatSP) || (newCoolSP && currentCoolSP != newCoolSP))) { // force the 'hold' or 'auto' event in 'auto' mode when heatSP or coolSP has changed manually
		sendEvent(name:"programScheduleName", value: programScheduleName, displayed:((settings.trace) ?:false), isStateChange:true)
		traceEvent(settings.logFilter,"refresh_thermostat>hold detected, currentHeatSP=$currentHeatSP,newHeatSP=$newHeatSP,currentCoolSP=$currentCoolSP,newCoolSP=$newCoolSP",
			settings.trace)
	}	
    
	def progDisplayName = getCurrentProgName()
	def currentSetClimate= (device.currentValue("setClimate"))?:  currentClimate.name   
	String currentClimateTemplate= (foundEventClimateRef)? eventClimate.name: (!foundEvent)? currentClimate.name: currentSetClimate  // if no program's climate set, then use current program
	traceEvent(settings.logFilter,"refresh_thermostat>thermostatId = ${thermostatId},Current Climate Ref=${data.thermostatList[0].program.currentClimateRef},currentClimateTemplate=${currentClimateTemplate}",
			settings.trace)            
       
	if (foundEvent) {
		traceEvent(settings.logFilter,"refresh_thermostat>thermostatId = ${thermostatId},indiceEvent=${indiceEvent}",settings.trace)
		traceEvent(settings.logFilter,"refresh_thermostat>thermostatId = ${thermostatId},event name=${data.thermostatList[0]?.events[indiceEvent]?.name}",settings.trace)
		traceEvent(settings.logFilter,"refresh_thermostat>thermostatId = ${thermostatId},event type=${data.thermostatList[0]?.events[indiceEvent]?.type}",settings.trace)
		traceEvent(settings.logFilter,"refresh_thermostat>thermostatId = ${thermostatId},event's coolHoldTemp=${data.thermostatList[0]?.events[indiceEvent]?.coolHoldTemp}",settings.trace)
		traceEvent(settings.logFilter,"refresh_thermostat>thermostatId = ${thermostatId},event's heatHoldTemp=${data.thermostatList[0]?.events[indiceEvent]?.heatHoldTemp}",,settings.trace)
		traceEvent(settings.logFilter,"refresh_thermostat>thermostatId = ${thermostatId},event's fan mode=${data.thermostatList[0]?.events[indiceEvent]?.fan}",settings.trace)
		traceEvent(settings.logFilter,"refresh_thermostat>thermostatId = ${thermostatId},event's fanMinOnTime=${data.thermostatList[0]?.events[indiceEvent]?.fanMinOnTime}",settings.trace)
		traceEvent(settings.logFilter,"refresh_thermostat>thermostatId = ${thermostatId},event's vent mode=${data.thermostatList[0]?.events[indiceEvent]?.vent}",settings.trace)
		traceEvent(settings.logFilter,"refresh_thermostat>thermostatId = ${thermostatId},event's ventilatorMinOnTime=${data.thermostatList[0]?.events[indiceEvent]?.ventilatorMinOnTime}",
			settings.trace)            
		traceEvent(settings.logFilter,"refresh_thermostat>thermostatId = ${thermostatId},event's running=${data.thermostatList[0]?.events[indiceEvent]?.running}",settings.trace)
		traceEvent(settings.logFilter,"refresh_thermostat>thermostatId = ${thermostatId},event's holdClimateRef =${foundEventClimateRef}",settings.trace)
	}
	def currentHeatingSetpoint= device.currentValue("heatingSetpointDisplay")
	def currentCoolingSetpoint=device.currentValue("coolingSetpointDisplay")
	currentHeatingSetpoint=currentHeatingSetpoint?.toDouble()    
	currentCoolingSetpoint=currentCoolingSetpoint?.toDouble()    
	String thermostatSetpointString    
    
	if (data.thermostatList[0]?.settings?.hvacMode=='auto') {  
		double medianPoint= ((currentHeatingSetpoint + currentCoolingSetpoint)/2).toDouble()
		if (scale == "C") {
			thermostatSetpointString = String.format('%2.1f', medianPoint)
		} else {
			thermostatSetpointString= String.format('%2d', medianPoint.intValue())            
		}
	}        
	if (data.thermostatList[0]?.settings?.hvacMode in ['heat', 'off']) {  
		if (scale == "C") {
			thermostatSetpointString = String.format('%2.1f',currentHeatingSetpoint)
		} else {
			thermostatSetpointString= String.format('%2d', currentHeatingSetpoint.intValue())            
		}
	}    
	if (data.thermostatList[0]?.settings?.hvacMode=='cool') {  
		if (scale == "C") {
			thermostatSetpointString = String.format('%2.1f',currentCoolingSetpoint)
		} else {
			thermostatSetpointString= String.format('%2d', currentCoolingSetpoint.intValue())            
		}
	}    
    

	def supportedThermostatModes=['off']
	def heatStages=device.currentValue("heatStages")       
	def coolStages=device.currentValue("coolStages")
	coolStages=coolStages?.toInteger()     
	heatStages=heatStages?.toInteger()     
	if ((coolStages)|| (data.thermostatList[0]?.settings?.hasHeatPump==true)) {
		supportedThermostatModes << 'cool' 
	}        
	if ((data.thermostatList[0]?.settings?.hasHeatPump==true) || (data.thermostatList[0]?.settings?.hasForcedAir==true)  || 
			(data.thermostatList[0]?.settings?.hasElectric==true) || (data.thermostatList[0]?.settings?.hasBoiler==true)) {
		supportedThermostatModes << 'heat' 
	}    
	if (data.thermostatList[0]?.settings?.hasHeatPump==true) {
		supportedThermostatModes << 'auxHeatOnly' << 'emergency heat'  
	}        
	if (supportedThermostatModes.contains("heat") &&  supportedThermostatModes.contains("cool")) {
		supportedThermostatModes << 'auto' 
	}        

    
	def supportedThermostatFanModes=[]
	supportedThermostatFanModes << 'auto' << 'circulate' << 'off' << 'on'    
    
	def alerts=getAlerts()
	dataEvents = [
 		thermostatId:data.thermostatList[0]?.identifier?.toString(),
 		thermostatName:data.thermostatList[0]?.name,
		thermostatMode:data.thermostatList[0]?.settings?.hvacMode,
		temperature: data.thermostatList[0]?.runtime?.actualTemperature,
		temperatureDisplay: data.thermostatList[0]?.runtime?.actualTemperature,
		humidity:data.thermostatList[0]?.runtime?.actualHumidity,
		thermostatSetpoint:thermostatSetpointString,
		equipmentStatus:getEquipmentStatus(),
		thermostatOperatingState: getThermostatOperatingState(),
		hasHumidifier:data.thermostatList[0]?.settings?.hasHumidifier?.toString(),
		hasDehumidifier:data.thermostatList[0]?.settings?.hasDehumidifier?.toString(),
		hasHrv:data.thermostatList[0]?.settings?.hasHrv?.toString(),
		hasErv:data.thermostatList[0]?.settings?.hasErv?.toString(),
		dehumidifyWithAC:data.thermostatList[0]?.settings?.dehumidifyWithAC?.toString(),
		dehumidifyOvercoolOffset: data.thermostatList[0]?.settings?.dehumidifyACOffset?.toString(),       
		programEndTimeMsg: (foundEvent)? "${data.thermostatList[0]?.events[indiceEvent]?.type}" +
			" ends at \r${data.thermostatList[0]?.events[indiceEvent]?.endDate} " +
			"${data.thermostatList[0]?.events[indiceEvent]?.endTime?.substring(0,5)}":
 			"No Events running",
		thermostatFanMode: (foundEvent)? data.thermostatList[0]?.events[indiceEvent]?.fan: 
			data.thermostatList[0]?.runtime?.desiredFanMode,
		fanMinOnTime: data.thermostatList[0]?.settings?.fanMinOnTime?.toString(),
		programFanMode: (data.thermostatList[0]?.settings?.hvacMode == 'cool')? currentClimate?.coolFan : currentClimate?.heatFan,
		programNameForUI: progDisplayName,
		weatherStation:data.thermostatList[0]?.weather?.weatherStation,
		weatherSymbol:data.thermostatList[0]?.weather?.forecasts[0]?.weatherSymbol?.toString(),
		weatherTemperature:data.thermostatList[0]?.weather?.forecasts[0]?.temperature,
		weatherTemperatureDisplay:data.thermostatList[0]?.weather?.forecasts[0]?.temperature,
		weatherDateTime:"Weather ${data.thermostatList[0]?.weather?.forecasts[0]?.dateTime?.substring(0,16)}",
		weatherCondition:data.thermostatList[0]?.weather?.forecasts[0]?.condition,
		weatherTemp: data.thermostatList[0]?.weather?.forecasts[0]?.temperature,
		weatherTempDisplay: data.thermostatList[0]?.weather?.forecasts[0]?.temperature,
		weatherTempHigh: data.thermostatList[0]?.weather?.forecasts[0]?.tempHigh, 
		weatherTempLow: data.thermostatList[0]?.weather?.forecasts[0]?.tempLow,
		weatherTempHighDisplay: data.thermostatList[0]?.weather?.forecasts[0]?.tempHigh, 
		weatherTempLowDisplay: data.thermostatList[0]?.weather?.forecasts[0]?.tempLow,
		weatherWindSpeed: (data.thermostatList[0]?.weather?.forecasts[0]?.windSpeed/1000),		// divided by 1000 for display
		weatherPressure:data.thermostatList[0]?.weather?.forecasts[0]?.pressure?.toString(),
		weatherRelativeHumidity:data.thermostatList[0]?.weather?.forecasts[0]?.relativeHumidity,
		weatherWindDirection:data.thermostatList[0]?.weather?.forecasts[0]?.windDirection + " Winds",
		weatherPop:data.thermostatList[0]?.weather?.forecasts[0]?.pop?.toString(),        
		programCoolTemp:currentClimate?.coolTemp,									
		programHeatTemp:currentClimate?.heatTemp,
		programCoolTempDisplay:currentClimate?.coolTemp,								
		programHeatTempDisplay:currentClimate?.heatTemp,
		alerts: ((alerts)? alerts :'None'),
//		groups: (ecobeeType.toUpperCase() == 'REGISTERED')? getThermostatGroups(thermostatId) : 'No groups',
		climateList: getClimateList(),
		supportedClimatePrograms: getSupportedClimatePrograms()?.toString(),        
		presence: (progDisplayName?.toUpperCase()?.contains('AWAY'))? "not present":"present",
		climateName: currentClimate?.name,
		setClimate: currentClimateTemplate,
		playbackVolume: data.thermostatList[0]?.audio?.playbackVolume.toString(),
		microphoneEnabled: data.thermostatList[0]?.audio?.microphoneEnabled.toString(),	
		soundAlertVolume: data.thermostatList[0]?.audio?.soundAlertVolume.toString(),
		soundTickVolume:data.thermostatList[0]?.audio?.soundTickVolume.toString(),
		voiceEngine:data.thermostatList[0]?.audio?.voiceEngine.toString(),
		backlightOnIntensity:data.thermostatList[0]?.settings?.backlightOnIntensity?.toString(),
		backlightSleepIntensity:data.thermostatList[0]?.settings?.backlightSleepIntensity?.toString(),   
		backlightOffTime:data.thermostatList[0]?.settings?.backlightOffTime?.toString(),
		heatCoolMinDelta:data.thermostatList[0]?.settings?.heatCoolMinDelta,      
		stage1CoolingDissipationTime:data.thermostatList[0]?.settings?.stage1CoolingDissipationTime.toString(),        
		stage1HeatingDissipationTime:data.thermostatList[0]?.settings?.stage1HeatingDissipationTime.toString(),        
		compressorProtectionMinTime:data.thermostatList[0]?.settings?.compressorProtectionMinTime.toString(),
		compressorProtectionMinTemp:data.thermostatList[0]?.settings?.compressorProtectionMinTemp
        
	]
	state?.supportedThermostatFanModes= supportedThermostatFanModes
	state?.supportedThermostatModes= supportedThermostatModes
    
	if (foundEvent && (data.thermostatList[0]?.events[indiceEvent]?.type?.toUpperCase() == 'QUICKSAVE')) {
		dataEvents.programEndTimeMsg ="Quicksave running"
	}
    
	generateEvent(dataEvents)
	if (data.thermostatList[0]?.settings?.hasHumidifier==true) {
		dataEvents = [
			humidifierMode: data.thermostatList[0]?.settings?.humidifierMode,
			humidifierLevel:data.thermostatList[0]?.settings?.humidity,
			targetHumidity: data.thermostatList[0]?.settings?.humidity           
		]
		generateEvent(dataEvents)        
	}
	if (data.thermostatList[0]?.settings?.hasDehumidifier==true) {
		dataEvents = [
			dehumidifierMode: data.thermostatList[0]?.settings?.dehumidifierMode,
			dehumidifierLevel:data.thermostatList[0]?.settings?.dehumidifierLevel,
			targetHumidity: data.thermostatList[0]?.settings?.dehumidifierLevel           
		]            
		generateEvent(dataEvents)                    
	}
	if ((data.thermostatList[0]?.settings?.hasHrv== true) || (data?.thermostatList[0]?.settings?.hasErv==true)) {
		dataEvents = [
			ventilatorMinOnTime:data.thermostatList[0]?.settings?.ventilatorMinOnTime?.toString(),
			ventilatorMode: data.thermostatList[0]?.settings?.vent
		]            
		generateEvent(dataEvents)                    
	}
	if (heatStages>1 || ((supportedThermostatModes.contains("auxHeatOnly")))) {
		dataEvents = [
			auxMaxOutdoorTemp:data.thermostatList[0]?.settings?.auxMaxOutdoorTemp,
			stage1HeatingDifferentialTemp:data.thermostatList[0]?.settings?.stage1HeatingDifferentialTemp
		]            
		generateEvent(dataEvents)                    
	}
	if (coolStages>1) {
		dataEvents = [
			stage1CoolingDifferentialTemp:data.thermostatList[0]?.settings?.stage1CoolingDifferentialTemp        
		]            
		generateEvent(dataEvents)                    
	}

	def isChanged= isStateChange(device, "supportedThermostatModes", supportedThermostatModes?.toString())    
	sendEvent(name: "supportedThermostatModes", value: supportedThermostatModes, isStateChange: isChanged, displayed: (settings.trace?:false))	
	isChanged= isStateChange(device, "supportedThermostatFanModes", supportedThermostatFanModes?.toString())    
	sendEvent(name: "supportedThermostatFanModes", value: supportedThermostatFanModes, isStateChange: isChanged, displayed: (settings.trace?:false))	

	def low_heating=device.currentValue("heatingSetpointRangeLow")
	def high_heating = device.currentValue("heatingSetpointRangeHigh")  
	def heatingSetpointRange= [low_heating,high_heating]
	isChanged= isStateChange(device, "heatingSetpointRange", heatingSetpointRange?.toString())    
	sendEvent(name: "heatingSetpointRange", value: heatingSetpointRange, isStateChange: isChanged, displayed: (settings.trace?:false))
	def low_cooling= device.currentValue("coolingSetpointRangeLow")
	def high_cooling = device.currentValue("coolingSetpointRangeHigh")   
	def coolingSetpointRange= [low_cooling,high_cooling]
	isChanged= isStateChange(device, "coolingSetpointRange", coolingSetpointRange?.toString())    
	sendEvent(name: "coolingSetpointRange", value: coolingSetpointRange, isStateChange: isChanged, displayed: (settings.trace?:false))	
	def thermostatSetpointRange= [low_heating,high_cooling]
	isChanged= isStateChange(device, "thermostatSetpointRange", thermostatSetpointRange?.toString())    
	sendEvent(name: "thermostatSetpointRange", value: thermostatSetpointRange?.toString(), isStateChange: isChanged, displayed: (settings.trace?:false))	
	traceEvent(settings.logFilter,"refresh_thermostat>done for thermostatId =${thermostatId}", settings.trace)

}

// refresh() has a different polling interval as it is called by the UI (contrary to poll).
void refresh() {
	settings.trace=true
    settings.logFilter=5    
	Date endDate= new Date()
	Date startDate = endDate -1    
	def thermostatId= determine_tstat_id("") 	    
	def poll_interval=0.25   // set a 15 sec. poll interval to avoid unecessary load on ecobee servers
	def time_check_for_poll = (now() - (poll_interval * 60 * 1000))
	if ((state?.lastPollTimestamp) && (state?.lastPollTimestamp > time_check_for_poll)) {
		traceEvent(settings.logFilter,"refresh>thermostatId = ${thermostatId},time_check_for_poll (${time_check_for_poll} < state.lastPollTimestamp (${state.lastPollTimestamp}), not refreshing data...",
			settings.trace)	            
		return
	}
	def ecobeeType = determine_ecobee_type_or_location(tstatType)
	getThermostatSummary(ecobeeType, false)
	state.lastPollTimestamp = now()
	getThermostatInfo(thermostatId, false)
    refresh_thermostat(thermostatId)    
  
}


// 	thermostatId - Id of the thermostat, by default the current one  
// 	eventFields - event record as generated by Ecobee for RT events 
void generateRTEvents(thermostatId, eventFields) {
    
	thermostatId=determine_tstat_id(thermostatId)
    
	if (eventFields) {
		for (field in eventFields) {

			traceEvent(settings.logFilter,"generateRTEvents>about to process ${field.name}=${field.value}", settings.trace, GLOBAL_LOG_INFO)
			data.thermostatList[0]."${field.structure}"."${field.name}"=${field.value}

		}   /* end for field */     

		refresh_thermostat(thermostatId, null, true) // refresh the thermostat with RT events

	} /* end if eventFields */ 
    
	traceEvent(settings.logFilter,"generateRTEvents>done for thermostatId =${thermostatId}", settings.trace)
}

void poll() {
	String URI_ROOT = "${get_URI_ROOT()}/${get_API_VERSION()}"
    
	def thermostatId= determine_tstat_id("") 	    
	def ecobeeType = determine_ecobee_type_or_location("")

	def poll_interval=1   // set a minimum of 1 min. poll interval to avoid unecessary load on ecobee servers
	def time_check_for_poll = (now() - (poll_interval * 60 * 1000))
	if ((state?.lastPollTimestamp) && (state?.lastPollTimestamp > time_check_for_poll)) {
		traceEvent(settings.logFilter,"poll>thermostatId = ${thermostatId},time_check_for_poll (${time_check_for_poll} < state.lastPollTimestamp (${state.lastPollTimestamp}), not refreshing data...",
			settings.trace, GLOBAL_LOG_INFO)            
		return
	}
   
	if (!thermostat_revision_changed(ecobeeType,thermostatId)) {
    
		// if there are no changes in the thermostat, runtime or interval revisions, stop the polling as values at ecobee haven't changed since last poll()
		return
	}
/*
	if (isTokenExpired()) {
		traceEvent(settings.logFilter,"poll>need to refresh tokens", settings.trace, GLOBAL_LOG_WARN)
       
		if (!refresh_tokens()) {
			traceEvent(settings.logFilter,"poll>not able to renew the refresh token", settings.trace, GLOBAL_LOG_WARN)         
		} else {
        
			// Reset Exceptions counter as the refresh_tokens() call has been successful 
			state?.exceptionCount=0
		}            
        
	}
*/    
/*    
	def bodyReq = build_body_request('thermostatInfo',null,thermostatId,null)
	traceEvent(settings.logFilter,"poll>about to call pollAsyncResponse with body = ${bodyReq} for thermostatId = ${thermostatId}...", settings.trace)
	    
	def args_encoded = java.net.URLEncoder.encode(bodyReq.toString(), "UTF-8")
	def request = [
			attributes: bodyReq
		]
    
	def params = [
		uri: "${URI_ROOT}/thermostat?format=json&body=${args_encoded}",
		headers: [
			'Authorization': "${data.auth.token_type} ${data.auth.access_token}",
			'Content-Type': "application/json",
			'charset': "UTF-8",
			'Accept': "application/json"
		]
	]
	asynchttp_v1.get('pollAsyncResponse',params, request)
*/
//	getThermostatInfo("", true)
	refresh_thermostat(thermostatId)    
	traceEvent(settings.logFilter,"poll>done for thermostatId =${thermostatId}", settings.trace)

}

def pollAsyncResponse(response, data) {	
	def TOKEN_EXPIRED=401
	def attributes=data?.attributes  
    
	if (response.hasError()) {
		if (response?.status == TOKEN_EXPIRED) { // token is expired
			traceEvent(settings.logFilter,"pollAsyncResponse>ecobee's Access token has expired for $attributes, trying to refresh tokens now...", settings.trace, GLOBAL_LOG_WARN)
			refresh_tokens()      
		            
		} else {         
			traceEvent(settings.logFilter,"pollAsyncResponse>ecobee response error: $response.errorMessage for $attributes", true, GLOBAL_LOG_ERROR)
			state?.exceptionCount=state?.exceptionCount +1        
		}        
	} else {
		def responseValues=null    
		try {
			// json response already parsed into JSONElement object
			responseValues = response.json    
		} catch (e) {
			traceEvent(settings.logFilter,"pollAsyncResponse>ecobee - error parsing json from response: $e", settings.trace, GLOBAL_LOG_ERROR)
			return            
		}
		if (responseValues) {
			data?.thermostatList=responseValues.thermostatList			            
			def thermostatId = responseValues?.thermostatList[0].identifier       
               
			if (settings.trace) {
				def thermostatName = responseValues?.thermostatList[0]?.name           
				traceEvent(settings.logFilter,
					"pollAsyncResponse> thermostatId=${thermostatId},name=${thermostatName},hvacMode=${data?.thermostatList[0]?.settings?.hvacMode}," +
					"fan=${data?.thermostatList[0]?.runtime?.desiredFanMode},desiredHeat=${data?.thermostatList[0]?.runtime?.desiredHeat}," +
					"desiredCool=${data?.thermostatList[0]?.runtime?.desiredCool},lastWeatherUpdate=${data?.thermostatList[0].weather.forecasts[0].dateTime.substring(0,16)}", settings.trace)
//				log.debug
//					"pollAsyncResponse> thermostatId=${thermostatId},name=${thermostatName},hvacMode=${data?.thermostatList[0]?.settings?.hvacMode}," +
//					"fan=${data?.thermostatList[0]?.runtime?.desiredFanMode},desiredHeat=${data?.thermostatList[0]?.runtime?.desiredHeat}," +
//					"desiredCool=${data?.thermostatList[0]?.runtime?.desiredCool},lastWeatherUpdate=${data?.thermostatList[0].weather.forecasts[0].dateTime.substring(0,16)}"
			}
			refresh_thermostat(thermostatId, responseValues) 
			state.lastPollTimestamp = now()
			state?.exceptionCount=0                 
			       
		}                
                
	}        
}    

private void generateEvent(Map results) {
	traceEvent(settings.logFilter,"generateEvent>parsing data $results", settings.trace)
    
	state?.scale = getTemperatureScale() // make sure to display in the right scale
	def scale = state?.scale
	if (results) {
		results.each { name, value ->
			def isDisplayed = true

			String upperFieldName=name.toUpperCase()    

// 			Temperature variable names for display contain 'display'            

			if (upperFieldName.contains("DISPLAY")) {  

				String tempValueString 
				double tempValue 
				if (scale == "C") {
					tempValue = getTemperatureValue(value).toDouble().round(1)
					tempValueString = String.format('%2.1f', tempValue)
					if ((upperFieldName.contains("PROGRAM") || upperFieldName.contains("SETPOINT"))) { 
						if (tempValueString.matches(".*([.,][3456])")) {                
							tempValueString=String.format('%2d.5', tempValue.intValue())                
							traceEvent(settings.logFilter,"generateEvent>$name has value $tempValueString which ends with 3456=> rounded to .5", settings.trace)	
						} else if (tempValueString.matches(".*([.,][789])")) {  
							traceEvent(settings.logFilter,"generateEvent>$name has value $tempValueString which ends with 789=> rounded to next .0", settings.trace)	
							tempValue=tempValue.intValue() + 1                        
							tempValueString=String.format('%2d.0', tempValue.intValue())               
						} else {
							traceEvent(settings.logFilter,"generateEvent>$name has value $tempValueString which ends with 012=> rounded to previous .0", settings.trace)	
							tempValueString=String.format('%2d.0', tempValue.intValue())               
						}
						tempValue= tempValueString.toDouble().round(1)                        
					}
				                    
				} else {
					tempValue = getTemperatureValue(value).toDouble().round()
					tempValueString = String.format('%2d', tempValue.intValue())            
				}
                
				def isChange = isStateChange(device, name, tempValue.toString())
                
				isDisplayed = isChange
				sendEvent(name: name, value: tempValueString, unit: scale, displayed: isDisplayed)                                     									 

			} else if (upperFieldName.contains("THERMOSTATSETPOINT")) {  
				def isChange = isStateChange(device, name, value)		// take value as is -don't transform it as it's been calculated already
				isDisplayed = isChange

				sendEvent(name: name, value: value, isStateChange: isChange, displayed: isDisplayed)       

	 		} else if ((upperFieldName.contains("DELTA")) || (upperFieldName.contains("DIFFERENTIALTEMP"))) {
				double tempValue 
				String tempValueString                 
				if (scale == "C") {
					tempValue = (value/10 * 5/9).toDouble().round(1)
					tempValueString = String.format('%2.1f', tempValue)
				} else {
					tempValue = (value/10).toDouble().round()
					tempValueString=String.format('%2d.0', tempValue.intValue())               
				}                
				def isChange = isStateChange(device, name,  tempValueString)
				isDisplayed = isChange
				sendEvent(name: name, value: tempValueString, unit: scale, displayed: isDisplayed)

// 			Temperature variable names contain 'temp' or 'setpoint' (not for display)           

			} else if ((upperFieldName.contains("TEMP")) || (upperFieldName.contains("SETPOINT"))) {  
                                
				double tempValue = getTemperatureValue(value).toDouble().round(1)
				String tempValueString = String.format('%2.1f', tempValue)
				if (scale == "C") {
					if ((upperFieldName.contains("PROGRAM") || upperFieldName.contains("SETPOINT"))) { 
						if (tempValueString.matches(".*([.,][3456])")) {                
							tempValueString=String.format('%2d.5', tempValue.intValue())                
							traceEvent(settings.logFilter,"generateEvent>$name has value $tempValueString which ends with 3456=> rounded to .5", settings.trace)	
						} else if (tempValueString.matches(".*([.,][789])")) {  
							traceEvent(settings.logFilter,"generateEvent>$name has value $tempValueString which ends with 789=> rounded to next .0", settings.trace)	
							tempValue=tempValue.intValue() + 1                        
							tempValueString=String.format('%2d.0', tempValue.intValue())               
						} else {
							traceEvent(settings.logFilter,"generateEvent>$name has value $tempValueString which ends with 012=> rounded to previous .0", settings.trace)	
							tempValueString=String.format('%2d.0', tempValue.intValue())               
						}
					}
				}
				def isChange = isStateChange(device, name,  tempValueString)
				isDisplayed = isChange
				sendEvent(name: name, value: tempValueString, unit: scale, displayed: isDisplayed)
                
			} else if (upperFieldName.contains("SPEED")) {

// 			Speed variable names contain 'speed'

 				double speedValue = getSpeed(value).round(1)
				def isChange = isStateChange(device, name, speedValue.toString())
				isDisplayed = isChange
				sendEvent(name: name, value: speedValue.toString(), unit: getDistanceScale(), displayed: isDisplayed)                                     									 
			} else if ((upperFieldName.contains("HUMIDITY")) || (upperFieldName.contains("LEVEL"))) {
 				double humValue = value.toDouble().round(0)
				String humValueString = String.format('%2d', humValue.intValue())
				def isChange = isStateChange(device, name, humValueString)
				isDisplayed = isChange
				sendEvent(name: name, value: humValueString, unit: "%", displayed: isDisplayed)                                     									 
			} else if (upperFieldName.contains("DATA")) { // data variable names contain 'data'

				sendEvent(name: name, value: value, displayed: (settings.trace?:false))                                     									 

 			} else if (value != null && value != 'null' &&  value != '[:]' && value != '[]') {           
 				def isChange = isStateChange(device, name, value)
				isDisplayed = isChange

				sendEvent(name: name, value: value, isStateChange: isChange, displayed: isDisplayed)       
			}
		}
	}
}

private def getCurrentProgName() {
	def AWAY_PROG = 'Away'
	def SLEEP_PROG = 'Sleep'
	def HOME_PROG = 'Home'
	def AWAKE_PROG = 'Awake'
	def CUSTOM_PROG = 'Other'
	def QUICKSAVE = 'Save'

	// remove any suffix added to the program name

	def progCurrentName = device.currentValue("programScheduleName")
    progCurrentName = (progCurrentName) ?  progCurrentName.minus("_auto").minus("auto") : null
	def progType = device.currentValue("programType")
	progType = (progType == null) ? "": progType.trim().toUpperCase()	
	progCurrentName = (progCurrentName == null) ? "": progCurrentName.trim()
	if ((progCurrentName != AWAY_PROG) && (progCurrentName != SLEEP_PROG) && 
		(progCurrentName != AWAKE_PROG) &&
		(progCurrentName != HOME_PROG) && (progCurrentName != QUICKSAVE)) {
		progCurrentName = (progType == 'VACATION') ? AWAY_PROG : CUSTOM_PROG
	}
	return progCurrentName
}    

private def getAlerts() {
	
	def alerts = ""
	if (data.thermostatList[0]?.alerts) {
		for (i in 0..data.thermostatList[0].alerts.size() - 1) {
			if (!alerts.contains(data.thermostatList[0].alerts[i].notificationType)) {     
				// only add different alerts
				alerts =  alerts  + data.thermostatList[0].alerts[i].notificationType + ','

			}                
		}
	}	
	// Remove last comma
	if (alerts) {    
		alerts = alerts.substring(0,(alerts.length()-1))
	}        
	return alerts
}

void getAlertText(alertType) {
	if (data.thermostatList[0]?.alerts) {
		for (i in 0..data.thermostatList[0].alerts.size() - 1) {
			if (data.thermostatList[0].alerts[i].notificationType== alertType) {
				traceEvent(settings.logFilter,"getAlertText>found alertType=${data.thermostatList[0].alerts[i].notificationType}", settings.trace)	
				sendEvent (name: "alertText", value:data.thermostatList[0].alerts[i].text, displayed: settings.trace)
				return                
			}                
		}
	}
	sendEvent (name: "alertText", value: "", displayed: settings.trace ) 
}
private def getThermostatGroups(thermostatId) {

	def groupList = 'No groups'
	getGroups(thermostatId)
	if (data.groups) {
		groupList = 'Group(s) '
		def j=0
		for (i in 0..data.groups.size() - 1) {
			if (data.groups[i].groupName != '') {
				groupList = (j > 0) ? ' \r' + groupList + data.groups[i].groupName :
					groupList + data.groups[i].groupName
				j++    
			}        
		}
	}	
	return groupList
}

private def getTemperatureValue(value) {
	value = (value!=null)? value:0
	double farenheits = (value.toDouble()/10)  // divide by 10 before display
	if (state?.scale == "C") {
		return fToC(farenheits)
	} else {
		return farenheits
	}
}

private def getSpeed(value) {
	double miles = value
	if (state?.scale == "C"){
		return milesToKm(miles)
	} else {
		return miles
	}
}

private def getDistanceScale() {
	def scale= state?.scale
	if (scale == 'C') {
		return "kmh"
	}
	return "mph"
}


// Get the EquipmentStatus including all components (HVAC, fan, dehumidifier/humidifier,HRV/ERV, aux heat)
// To be called after a poll() or refresh() to have the latest status
def getEquipmentStatus() {
	def equipStatus = (data.thermostatList[0]?.equipmentStatus) ? 
		data.thermostatList[0].equipmentStatus + ' running' : 'Idle'
	return equipStatus
}
// Get the basic thermostat status (heating,cooling,fan only)
// To be called after a poll() or refresh() to have the latest status

def getThermostatOperatingState() {

	def equipStatus = getEquipmentStatus()

	if (!equipStatus) {
		return    
	}    
	equipStatus = equipStatus.trim().toUpperCase()
    
	def currentOpState = equipStatus?.contains('HEAT')? 'heating' : (equipStatus?.contains('COOL')? 'cooling' : 
    	equipStatus?.contains('FAN')? 'fan only': 'idle')
	return currentOpState
}

// thermostatId may only be a specific thermostatId or "" (for current thermostat)
// To be called after a poll() or refresh() to have the latest status
private def getClimateList(thermostatId=settings.thermostatId) {
	def climateList=""
    
	if (!data.thermostatList[0]?.program?.climates) {
		return    
	}    

	for (i in 0..data.thermostatList[0].program.climates.size() - 1) {
		climateList = data.thermostatList[0].program.climates[i].name  + "," + climateList
		        
	}
	traceEvent(settings.logFilter,"getClimateList>climateList=${climateList}", settings.trace)
	return climateList
}
// thermostatId may only be a specific thermostatId or "" (for current thermostat)
// To be called after a poll() or refresh() to have the latest status
private def getSupportedClimatePrograms(thermostatId=settings.thermostatId) {
	def climateList=[]
    
	if (!data.thermostatList[0]?.program?.climates) {
		return []    
	}    

	for (i in 0..data.thermostatList[0].program.climates.size() - 1) {
		climateList.add(data.thermostatList[0].program.climates[i].name)
		        
	}
	traceEvent(settings.logFilter,"getSupportedClimatePrograms>climateList=${climateList}", settings.trace)
	return climateList
}


void resumeThisTstat() {
	def thermostatId= determine_tstat_id("") 	    
	resumeProgram() 
	refresh_thermostat(thermostatId)
}

void updateChildData(objects) {
	traceEvent(settings.logFilter,"updateChildData>objects from parent=$objects",settings.trace,GLOBAL_LOG_TRACE)        
	def empty_list=[]    
    
	if (!data?.thermostatList) {
		data?.thermostatList=empty_list
	}    
	data?.thermostatList = objects
    
	traceEvent(settings.logFilter,"updateChildData>thermostatList=${data?.thermostatList}",settings.trace,GLOBAL_LOG_TRACE)
	traceEvent(settings.logFilter,"updateChildData>settings=${data?.thermostatList[0].settings}",settings.trace,GLOBAL_LOG_TRACE)
	traceEvent(settings.logFilter,"updateChildData>runtime=${data?.thermostatList[0].runtime}",settings.trace,GLOBAL_LOG_TRACE)
    
}

void updateChildStructure(objects) {
	traceEvent(settings.logFilter,"updateChildStructure>objects from parent=$objects",settings.trace,GLOBAL_LOG_TRACE)        
	def empty_list=[]    
    
	if (!data?.revisionList) {
		data?.revisionList=empty_list
	}    
	if (!data?.statusList) {
		data?.statusList=empty_list
	}    
	data?.revisionList = objects?.revisionList
	data?.thermostatCount=objects?.thermostatCount
	data?.statusList = objects?.statusList
    
	traceEvent(settings.logFilter,"updateChildStructure>revisionList=${data?.revisionList}, thermostatCount=${data?.thermostatCount}",settings.trace,GLOBAL_LOG_TRACE)        
}


private void api(method, args, success = {}) {
	def MAX_EXCEPTION_COUNT=5, ther
	String URI_ROOT = "${get_URI_ROOT()}/${get_API_VERSION()}"
    
 /*   
	if (!isLoggedIn()) {
		login()
		
	}   
*/    
	if (isTokenExpired()) {
		traceEvent(settings.logFilter,"api>need to refresh tokens",settings.trace)
       
		if (!refresh_tokens()) {
			if ((exceptionCheck) && (state.exceptionCount >= MAX_EXCEPTION_COUNT) && (exceptionCheck.contains("Unauthorized"))) {
				traceEvent(settings.logFilter,"api>$exceptionCheck, not able to renew the refresh token;need to re-login to ecobee via MyEcobeeInit....", true, GLOBAL_LOG_ERROR)         
			}
		} else {
        
			// Reset Exceptions counter as the refresh_tokens() call has been successful 
			state.exceptionCount=0
		}            
        
	}
    
	def args_encoded = java.net.URLEncoder.encode(args.toString(), "UTF-8")
	def methods = [
		'thermostatSummary': 
			[uri:"${URI_ROOT}/thermostatSummary?format=json&body=${args_encoded}", 
      			type:'get'],
		'thermostatInfo': 
			[uri:"${URI_ROOT}/thermostat?format=json&body=${args_encoded}", 
          		type: 'get'],
		'setThermostatSettings':
			[uri: "${URI_ROOT}/thermostat?format=json", type: 'post'],
		'setHold': 
			[uri: "${URI_ROOT}/thermostat?format=json", type: 'post'],
		'resumeProgram': 
			[uri: "${URI_ROOT}/thermostat?format=json", type: 'post'],
		'createVacation': 
			[uri: "${URI_ROOT}/thermostat?format=json", type: 'post'],
		'deleteVacation': 
			[uri: "${URI_ROOT}/thermostat?format=json", type: 'post'],
		'getGroups': 
			[uri: "${URI_ROOT}/group?format=json&body=${args_encoded}",
				type: 'get'],
		'updateGroup': 
			[uri: "${URI_ROOT}/group?format=json", type: 'post'],
		'updateClimate': 
			[uri: "${URI_ROOT}/thermostat?format=json", type: 'post'],
		'controlPlug': 
			[uri: "${URI_ROOT}/thermostat?format=json", type: 'post'],
		'updateAudio': 
			[uri: "${URI_ROOT}/thermostat?format=json", type: 'post'],
		'runtimeReport': 
			[uri:"${URI_ROOT}/runtimeReport?format=json&body=${args_encoded}", 
				type: 'get'],
		]
	def request = methods.getAt(method)
	traceEvent(settings.logFilter,"api> about to call doRequest with (unencoded) args = ${args}", settings.trace)
	doRequest(request.uri, args_encoded, request.type, success)
	if (state.exceptionCount >= MAX_EXCEPTION_COUNT) {
		def exceptionCheck=device.currentValue("verboseTrace")
		traceEvent(settings.logFilter,"api>error: found a high number of exceptions (${state.exceptionCount}), last exceptionCheck=${exceptionCheck}, about to reset counter",
			settings.trace, GLOBAL_LOG_ERROR)  
		if (!exceptionCheck.contains("Unauthorized")) {          
			state.exceptionCount = 0  // reset the counter as long it's not unauthorized exception
			sendEvent(name: "verboseTrace", value: "", displayed:(settings.trace?:false)) // reset verboseTrace            
		}            
	}        

}

// Need to be authenticated in before this is called. So don't call this. Call api.
private void doRequest(uri, args, type, success) {
	settings.trace=true
    settings.logFilter=5    
	def TOKEN_EXPIRED=401
	def params = [
		uri: uri,
		headers: [
			'Authorization': "${data.auth.token_type} ${data.auth.access_token}",
			'Content-Type': "application/json",
			'charset': "UTF-8",
			'Accept': "application/json"
		]
	]
	traceEvent(settings.logFilter,"doRequest>about to ${type} with uri ${params.uri}, (encoded)args= ${args}", settings.trace)
	try {
		traceEvent(settings.logFilter,"doRequest>about to ${type} with uri ${params.uri}, (encoded)args= ${args}",settings.trace)
		if (type == 'post') {
			params?.body = args
			httpPostJson(params, success)

		} else if (type == 'get') {
			httpGet(params, success)
		}
		/* when success, reset the exception counter */
		state.exceptionCount=0
		traceEvent(settings.logFilter,"doRequest>done with ${type}", settings.trace)

	} catch (java.net.UnknownHostException e) {
		traceEvent(settings.logFilter,"doRequest> Unknown host ${params.uri}", settings.trace, GLOBAL_LOG_ERROR)
	} catch (java.net.NoRouteToHostException e) {
		traceEvent(settings.logFilter,"doRequest>No route to host - check the URL ${params.uri} ", settings.trace, GLOBAL_LOG_ERROR)
	} catch (e) {
		traceEvent(settings.logFilter,"doRequest>exception $e for ${params.uri}", settings.trace, GLOBAL_LOG_ERROR)
		state.exceptionCount = state.exceptionCount +1 
		if (e?.response?.status== TOKEN_EXPIRED) {
			traceEvent(settings.logFilter,"doRequest>token expired ($e?.response?.status), trying to refresh tokens", settings.trace, GLOBAL_LOG_WARN)       
			refresh_tokens()  
		}    	
	} 
}


// tstatType =managementSet or registered (no spaces).  
//		registered is for SMART & SMART-SI thermostats, 
//		managementSet is for EMS thermostat
//		may also be set to a specific locationSet (ex. /Toronto/Campus/BuildingA)
//		may be set to null if not relevant for the given method
// thermostatId may be a list of serial# separated by ",", no spaces (ex. '123456789012,123456789013') 
private def build_body_request(method, tstatType="registered", thermostatId, tstatParams = [],tstatSettings = [], includeSensor=false) {
	settings.trace=true
    settings.logFilter=5    
	def selectionJson = null
	def selection = null  
    if (tstatType==null || tstatType=="") tstatType='registered'    
	if (method == 'thermostatSummary') {
		if (tstatType.trim().toUpperCase() == 'REGISTERED') {
			selection = [selection: [selectionType: 'registered', selectionMatch: '',
							includeEquipmentStatus: 'true']
						]
		} else {
			// If tstatType is different than managementSet, it is assumed to be locationSet specific (ex./Toronto/Campus/BuildingA)
			selection = (tstatType.trim().toUpperCase() == 'MANAGEMENTSET') ? 
				// get all EMS thermostats from the root
				[selection: [selectionType: 'managementSet', selectionMatch: '/',
					includeEquipmentStatus: 'true']
				] : // Or Specific to a location
				[selection: [selectionType: 'managementSet', selectionMatch: tstatType.trim(),
					includeEquipmentStatus: 'true']
				]
		}
		selectionJson = new groovy.json.JsonBuilder(selection)
		return selectionJson
	} else if (method == 'thermostatInfo') {
		selection = [selection: [selectionType: 'thermostats',
			selectionMatch: thermostatId,
			includeSettings: 'true',
			includeRuntime: 'true',
			includeProgram: 'true',           
			includeWeather: 'true',            
			includeAlerts: 'true',
			includeEvents: 'true',
			includeEquipmentStatus: 'true',
			includeSensors: 'true',
			includeAudio: 'true'            
			]
		]
		selectionJson = new groovy.json.JsonBuilder(selection)
		return selectionJson
	} else if (method == 'remoteSensorUpdate') {
		selection = [selection: [selectionType: 'thermostats',
			selectionMatch: thermostatId,
			includeSensors: 'true'
			]
		]
		selectionJson = new groovy.json.JsonBuilder(selection)
		return selectionJson
	} else {
		selection = [selectionType: 'thermostats', selectionMatch: thermostatId]
	}
	selectionJson = new groovy.json.JsonBuilder(selection)
	if ((method != 'setThermostatSettings') && (tstatSettings != null) && (tstatSettings != [])) {
		def function_clause = ((tstatParams != null) && (tsatParams != [])) ? 
			[type:method, params: tstatParams] : 
			[type: method]
		def bodyWithSettings = [
				functions: [function_clause], selection: selection,
				thermostat: [settings: tstatSettings]
			]
		def bodyWithSettingsJson = new groovy.json.JsonBuilder(bodyWithSettings)
		return bodyWithSettingsJson
	} else if (method == 'setThermostatSettings') {
		def bodyWithSettings = [
				selection: selection,thermostat: [settings: tstatSettings]
			]
		def bodyWithSettingsJson = new groovy.json.JsonBuilder(bodyWithSettings)
		return bodyWithSettingsJson
	} else if ((tstatParams != null) && (tsatParams != [])) {
		def function_clause = [type: method, params: tstatParams]
		def simpleBody = [functions: [function_clause], selection: selection]
		def simpleBodyJson = new groovy.json.JsonBuilder(simpleBody)
		return simpleBodyJson
	} else {
		def function_clause = [type: method]
		def simpleBody = [functions: [function_clause], selection: selection]
		def simpleBodyJson = new groovy.json.JsonBuilder(simpleBody)
		return simpleBodyJson
    }    
}


// iterateSetThermostatSettings: iterate thru all the thermostats under a specific account and set the desired settings
// tstatType =managementSet or registered (no spaces).  May also be set to a specific locationSet (ex./Toronto/Campus/BuildingA)
// settings can be anything supported by ecobee 
//		at https://www.ecobee.com/home/developer/api/documentation/v1/objects/Settings.shtml
void iterateSetThermostatSettings(tstatType, tstatSettings = []) {
	Integer MAX_TSTAT_BATCH = get_MAX_TSTAT_BATCH()
	def tstatlist = null
	Integer nTstats = 0

	def ecobeeType = determine_ecobee_type_or_location(tstatType)
	getThermostatSummary(ecobeeType)
	traceEvent(settings.logFilter,"iterateSetThermostatSettings>ecobeeType=${ecobeeType},about to loop ${data.thermostatCount} thermostat(s)",settings.trace)
	for (i in 0..data.thermostatCount - 1) {
		def thermostatDetails = data.revisionList[i].split(':')
		def Id = thermostatDetails[0]
		def thermostatName = thermostatDetails[1]
		def connected = thermostatDetails[2]
		if (connected == 'true') {
			if (nTstats == 0) {
				tstatlist = Id
				nTstats = 1
			}
			if ((nTstats > MAX_TSTAT_BATCH) || (i == (data.thermostatCount - 1))) { 
				// process a batch of maximum 25 thermostats according to API doc
				traceEvent(settings.logFilter,"iterateSetThermostatSettings>about to call setThermostatSettings for ${tstatlist}", settings.trace)
				setThermostatSettings("${tstatlist}",tstatSettings)
				tstatlist = Id
				nTstats = 1
			} else {
				tstatlist = tstatlist + "," + Id
				nTstats++ 
			}
		}
	}
}

// thermostatId may be a list of serial# separated by ",", no spaces (ex. '123456789012,123456789013') 
//	if no thermostatId is provided, it is defaulted to the current thermostatId 
// settings can be anything supported by ecobee at https://www.ecobee.com/home/developer/api/documentation/v1/objects/Settings.shtml
void setThermostatSettings(thermostatId,tstatSettings = []) {
	settings.trace=true
    settings.logFilter=5    
	def ECOBEE_NEED_TOKEN_REFRESH=14
	def TOKEN_EXPIRED=401    
   	thermostatId= determine_tstat_id(thermostatId) 	    
	traceEvent(settings.logFilter,"setThermostatSettings>called with values ${tstatSettings} for ${thermostatId}",settings.trace)
	def bodyReq = build_body_request('setThermostatSettings',null,thermostatId,null,tstatSettings)
	int statusCode=1
	def exceptionCheck   
	def interval    
    
	api('setThermostatSettings', bodyReq) {resp ->
		statusCode = resp?.data?.status?.code
		def message = resp?.data?.status?.message
		if ((resp?.status==TOKEN_EXPIRED) || ((resp?.status == 500) && (statusCode == ECOBEE_NEED_TOKEN_REFRESH))) {
			traceEvent(settings.logFilter,"setThermostatSettings>thermostatId=${thermostatId},error $statusCode, need to call refresh_tokens()", settings.trace, GLOBAL_LOG_WARN)
			refresh_tokens()     
		}
		exceptionCheck=device.currentValue("verboseTrace")
		if (!statusCode) {
			/* when success, reset the exception counter */
			state.exceptionCount=0
			if ((data?."replaySettingsId${state?.retriesSettingsCounter}" == null) ||
				(state?.retriesSettingsCounter > get_MAX_SETTER_RETRIES())) {          // reset the counter if last entry is null
				reset_replay_data('Settings')                
				state?.retriesSettingsCounter=0
			}            
			traceEvent(settings.logFilter,"setThermostatSettings>done for ${thermostatId}", settings.trace)
		} else {
			state.exceptionCount = state.exceptionCount +1     
			traceEvent(settings.logFilter,"setThermostatSettings> error=${statusCode.toString()},message=${message} for ${thermostatId}", true, GLOBAL_LOG_ERROR)
		} /* end if statusCode */	
	} /* end api call */                
	if (exceptionCheck?.contains("exception")) {
		sendEvent(name: "verboseTrace", value: "", displayed:(settings.trace?:false)) // reset verboseTrace            
		traceEvent(settings.logFilter,"setThermostatSettings>exception=${exceptionCheck}", true, GLOBAL_LOG_ERROR)
		statusCode=(statusCode)?:3 //internal error            
	}                
    
	if ((statusCode) && (!exceptionCheck?.contains("Bad Request"))) {
		state?.retriesSettingsCounter=(state?.retriesSettingsCounter?:0)+1            
		if (!(interval=get_exception_interval_delay( state?.retriesSettingsCounter))) {  
			traceEvent(settings.logFilter,"setThermostatSettings>error max retries, counter=${state?.retriesSettingsCounter}, exiting", true, GLOBAL_LOG_ERROR)
			reset_replay_data('Settings')                
			state?.retriesSettingsCounter=0
			return        
		}        
		data?."replaySettingsId${state?.retriesSettingsCounter}"=thermostatId
		data?."replaySettings${state?.retriesSettingsCounter}"=tstatSettings    
		traceEvent(settings.logFilter,"setThermostatSettings>about to call setThermostatSettingsReplay,interval=$interval,retries counter=${state?.retriesSettingsCounter}", true, GLOBAL_LOG_INFO)
		runIn(interval, "setThermostatSettingsReplay",[overwrite:true])        
	}    
    
}


void setThermostatSettingsReplay() {
	def exceptionCheck=""

	for (int i=1; (i<= get_MAX_SETTER_RETRIES()); i++) {
		def id = data?."replaySettingsId$i"
		if (id == null) continue  // already processed        
		def tstatSettings=data?."replaySettings$i"
		traceEvent(settings.logFilter,"setThermostatSettingsReplay>about to recall setThermostatSettings for thermostatId=$id,retries counter=$i" +
			",tstatSettings=$tstatSettings", true, GLOBAL_LOG_INFO)
		setThermostatSettings(id,tstatSettings)
		exceptionCheck=device.currentValue("verboseTrace")
		if (exceptionCheck?.contains("done")) {
			data?."replaySettingsId$i"=null        
		} /* end if */
	} /* end for */
	if (exceptionCheck?.contains("done")) { // if last command executed OK, then reset the counter
		traceEvent(settings.logFilter,"setThermostatSettingsReplay>resetting counter as last setThermostatSettingsReplay command was ok..." +
			",tstatSettings=$tstatSettings", true, GLOBAL_LOG_INFO)
		state?.retriesSettingsCounter=0
		reset_replay_data('Settings')                
	} /* end if */
        
}


// iterateSetHold: iterate thru all the thermostats under a specific account and create the hold event
// tstatType =managementSet or registered (no spaces).  May also be set to a specific locationSet (ex./Toronto/Campus/BuildingA)
// settings can be anything supported by ecobee 
//		at https://www.ecobee.com/home/developer/api/documentation/v1/objects/Settings.shtml
// extraHoldParams may be any other sethold or events properties  
//		see https://www.ecobee.com/home/developer/api/documentation/v1/objects/Event.shtml for more details
void iterateSetHold(tstatType, coolingSetPoint, heatingSetPoint, fanMode,
	tstatSettings = [], extraHoldParams=[]) {
	Integer MAX_TSTAT_BATCH = get_MAX_TSTAT_BATCH()
	def tstatlist = null
	Integer nTstats = 0
	def ecobeeType = determine_ecobee_type_or_location(tstatType)
	getThermostatSummary(ecobeeType)
	traceEvent(settings.logFilter,"iterateSetHold>ecobeeType=${ecobeeType},about to loop ${data.thermostatCount} thermostat(s)",settings.trace)

	for (i in 0..data.thermostatCount - 1) {
		def thermostatDetails = data.revisionList[i].split(':')
		def Id = thermostatDetails[0]
		def thermostatName = thermostatDetails[1]
		def connected = thermostatDetails[2]
		if (connected == 'true') {
			if (nTstats == 0) {
				tstatlist = Id
				nTstats = 1
			}
			if ((nTstats > MAX_TSTAT_BATCH) || (i == (data.thermostatCount - 1))) { 
				// process a batch of maximum 25 thermostats according to API doc
				traceEvent(settings.logFilter,"iterateSetHold>about to call setHold for ${tstatlist}", settings.trace)
				setHoldExtraParams("${tstatlist}", coolingSetPoint, heatingSetPoint, fanMode,
					tstatSettings, extraHoldParams)
				tstatlist = Id
				nTstats = 1
			} else {
				tstatlist = tstatlist + "," + Id
				nTstats++ 
			}
		}
	}
}

// thermostatId may be a list of serial# separated by ",", no spaces (ex. '"123456789012","123456789013"') 
// tstatSettings as a Map can be anything supported by ecobee at https://www.ecobee.com/home/developer/api/documentation/v1/objects/Settings.shtml
// This setHold function is kept for better compatibility with previous releases
void setHold(thermostatId, coolingSetPoint, heatingSetPoint, fanMode,
	tstatSettings = []) {
    
    // Call the setHoldExtraParams function with null value as extraHoldParams 
	setHoldExtraParams(thermostatId, coolingSetPoint, heatingSetPoint, fanMode,
		tstatSettings, null) 
}

// thermostatId may be a list of serial# separated by ",", no spaces (ex. '"123456789012","123456789013"') 
// holdType as a String can be 'indefinite', 'nextTransition', 'holdHours', 'dateTime': any holdType supported by ecobee at https://www.ecobee.com/home/developer/api/documentation/v1/functions/SetHold.shtml
// holdHours (optional) as an integer as indicated at https://www.ecobee.com/home/developer/api/documentation/v1/functions/SetHold.shtml, required for 'holdHOurs' holdType
// startDate (optional) as a String as indicated at https://www.ecobee.com/home/developer/api/documentation/v1/functions/SetHold.shtml required for 'dateTime' holdType, format: 'YYYY-MM-DD'
// startTime (optional)as a String as indicated at https://www.ecobee.com/home/developer/api/documentation/v1/functions/SetHold.shtml, format 'HH:MI:SS', default: '00:00:00'
// endDate (optional) as a String as indicated at https://www.ecobee.com/home/developer/api/documentation/v1/functions/SetHold.shtml, format: 'YYYY-MM-DD', default: '2035-01-01'
// endTime (optional) as a String as indicated at https://www.ecobee.com/home/developer/api/documentation/v1/functions/SetHold.shtml, format 'HH:MI:SS', default: '23:59:59'

// example1: device.setHoldWithHoldType('',27.5,17.5,null,'indefinite')		// to set an indefinite hold with coolingSetpoint=27.5, heatingSetpoint=17.5 on current thermostat
// example2: device.setHoldWithHoldType('',27.5,17.5,null,'holdHours',2)	// to set an holdHours of 2 hours with coolingSetpoint=27.5, heatingSetpoint=17.5 on current thermostat
// example3: device.setHoldWithHoldType('',28.5,16.5,null,'dateTime', null,'2018-07-11','16:28:00') // to set a "dateTime" holdType with coolingSetpoint=28.5, heatingSetpoint=16.5 starting on 2018-07-28 at 16:28 and ending in 2035-01-01

void setHoldWithHoldType(thermostatId, coolingSetPoint, heatingSetPoint, fanMode,
	holdType, holdHours=null, startDate=null, startTime=null, endDate=null, endTime=null) {

	thermostatId = determine_tstat_id(thermostatId)
	traceEvent(settings.logFilter,"setholdWithHoldType>called with values holdType=$holdType, coolSP=${coolingSetPoint}, heatSP=${heatingSetPoint}, fanMode=${fanMode} for ${thermostatId}", settings.trace)
	def uppercaseHoldType=holdType.trim().toUpperCase()    
	if (uppercaseHoldType.contains("NEXTTRANSITION")) { // to avoid any issue with the wrong case in the preference parameter.
		holdType="nextTransition"			        
	} else if (uppercaseHoldType.contains("INDEFINITE")) {
		holdType="indefinite"			        
	} else if (uppercaseHoldType.contains("HOLDHOURS")) {
		holdType="holdHours"            
	} else if (uppercaseHoldType.contains("DATETIME")) {
		holdType="dateTime"            
	} 
	if ((holdType == 'dateTime') && (startDate==null || startDate=="")) {
		traceEvent(settings.logFilter,"setHoldWithHoldType>error, the holdType $holdType requires startDate param to bet set for ${thermostatId}",true, GLOBAL_LOG_ERROR)
		return    
	} 
	if ((holdType == 'holdHours') && ((holdHours==null  || ((!holdHours.isNumber())) || holdHours <=0))) {
		traceEvent(settings.logFilter,"setHoldWithHoldType>error, the holdType $holdType requires holdHours param to bet set with an integer value greater than 0 for ${thermostatId}",true, GLOBAL_LOG_ERROR)
		return    
	} 
		    
    // Determine the right hold Params
    
	def holdParams=[:]

	if (holdType in ['nextTransition', 'indefinite']) {    
		holdParams=["holdType": "${holdType}"]
	} else if (holdType == 'holdHours') {
		holdParams= [holdType: "${holdType}", holdHours: holdHours]
	} else if (holdType == 'dateTime') {
		if (startTime == null || startTime =="") {
			startTime="00:00:00"                
		}                
		if (endDate == null || endDate == "") {
			endDate="2035-01-01"                
		}                
		if (endTime == null || endTime == "") {
			endTime="23:59:59"                
		}                
		holdParams= [holdType:holdType,startDate:startDate,startTime:startTime,endDate:endDate,endTime:endTime]
	}    
	setHoldExtraParams(thermostatId, coolingSetPoint, heatingSetPoint, fanMode,
		 null, holdParams) 
}


// New version of setHold with access to extra setHold params when needed (ex. to set ventilator event's properties).
// thermostatId may be a list of serial# separated by ",", no spaces (ex. '123456789012,123456789013') 
//	if no thermostatId is provided, it is defaulted to the current thermostatId 
// settings can be anything supported by ecobee at https://www.ecobee.com/home/developer/api/documentation/v1/objects/Settings.shtml
// extraHoldParams may be any other sethold or events properties  
//		see https://www.ecobee.com/home/developer/api/documentation/v1/objects/Event.shtml
void setHoldExtraParams(thermostatId, coolingSetPoint, heatingSetPoint, fanMode,
	tstatSettings = [], extraHoldParams=[]) {    
	def ECOBEE_NEED_TOKEN_REFRESH=14
	def TOKEN_EXPIRED=401    
	def holdType,holdHours,startDate,startTime,endDate,endTime  
	def interval
    
	Integer targetCoolTemp = null,targetHeatTemp = null
	def tstatParams = null
	thermostatId = determine_tstat_id(thermostatId)
	traceEvent(settings.logFilter,"sethold>called with values coolSP=${coolingSetPoint}, heatSP=${heatingSetPoint}, fanMode=${fanMode}, settings=${tstatSettings} for ${thermostatId}", settings.trace)
	def scale = state?.scale
	if (scale == 'C') {
		targetCoolTemp = (cToF(coolingSetPoint) * 10) // need to send temperature in F multiply by 10
		targetHeatTemp = (cToF(heatingSetPoint) * 10)
	} else {
		targetCoolTemp = coolingSetPoint * 10 // need to send temperature in F multiply by 10
		targetHeatTemp = heatingSetPoint * 10
	}
	/* if settings.holdType has a value, include it in the list of params
	 */
	if (((settings.holdType != null) && (settings.holdType.trim() != "")) && 
		((!extraHoldParams) || ((extraHoldParams) && (!(extraHoldParams.find{name, value -> name.toUpperCase() == 'HOLDTYPE'}))))) {
		def uppercaseHoldType=settings.holdType.trim().toUpperCase()    
		if (uppercaseHoldType.contains("NEXTTRANSITION")) { // to avoid any issue with the wrong case in the preference parameter.
			holdType="nextTransition"			        
		} else if (uppercaseHoldType.contains("INDEFINITE")) {
			holdType="indefinite"			        
		} else if (uppercaseHoldType.contains("HOLDHOURS")) {
			holdType="holdHours"            
			int posHoldHours=find_delimited_substring(uppercaseHoldType,'HOLDHOURS')
			holdHours=extract_delimited_substring(uppercaseHoldType,(posHoldHours+10))
			holdHours= (holdHours) ? holdHours.toInteger() :1 // by default, holdHours=1            
		} else if (uppercaseHoldType.contains("STARTDATE")) {
			holdType="dateTime"            
			int posStartDate=find_delimited_substring(uppercaseHoldType,'STARTDATE')
			int posStartTime=find_delimited_substring(uppercaseHoldType,'STARTTIME')
			int posEndDate=find_delimited_substring(uppercaseHoldType,'ENDDATE')                       
			int posEndTime=find_delimited_substring(uppercaseHoldType,'ENDTIME')                       
			if (posStartDate != -1) {
				startDate=extract_delimited_substring(uppercaseHoldType,(posStartDate+10))
			}        
			if (posStartTime != -1) {
				startTime=extract_delimited_substring(uppercaseHoldType,(posStartTime+10))
			} else {
				startTime="00:00:00"                
			}                
			if (posEndDate != -1) {
				endDate= extract_delimited_substring(uppercaseHoldType,(posEndDate+8))
			} else {
				endDate="2035-01-01"                
			}                
			if (posEndTime != -1) {
				endTime=extract_delimited_substring(uppercaseHoldType,(posEndTime+8))
			} else {
				endTime="23:59:59"                
			}                
		}        
		tstatParams = ((fanMode != null) & (fanMode != "")) ? 
          		[coolHoldTemp:targetCoolTemp, heatHoldTemp: targetHeatTemp, fan: fanMode, 
             			holdType:"${holdType}"
             		] : 
        		[coolHoldTemp: targetCoolTemp, heatHoldTemp: targetHeatTemp, 
             			holdType:"${holdType}"
			]
		if (holdType == "holdHours") {             
			tstatParams=tstatParams + [holdHours:holdHours]        
		}        
		if ((holdType == "dateTime") && (startDate && startTime && endDate && endTime)) {             
			tstatParams=tstatParams + ['startDate':startDate, 'startTime':startTime, 'endDate':endDate, 'endTime':endTime]                       
		}        
	} else {
		tstatParams = ((fanMode != null) & (fanMode != "")) ? 
			[coolHoldTemp:targetCoolTemp, heatHoldTemp: targetHeatTemp, fan: fanMode] : 
        	[coolHoldTemp: targetCoolTemp, heatHoldTemp: targetHeatTemp]

	}
	// Add the extraHoldParams if any
 	if ((extraHoldParams != null) && (extraHoldParams != [])) {
		tstatParams = tstatParams + extraHoldParams
    		
	}
	def bodyReq = build_body_request('setHold',null,thermostatId,tstatParams,tstatSettings)
	traceEvent(settings.logFilter,"setHoldWithExtraParams>thermostatId=${thermostatId}, bodyReq=$bodyReq",settings.trace)                
	    
	int statusCode=1
	def exceptionCheck    
	api('setHold', bodyReq) {resp ->
		statusCode = resp?.data?.status?.code
		def message = resp?.data?.status?.message
		if ((resp?.status==TOKEN_EXPIRED) || ((resp?.status == 500) && (statusCode == ECOBEE_NEED_TOKEN_REFRESH))) {
			traceEvent(settings.logFilter,"setHold>thermostatId=${thermostatId},error $statusCode, need to call refresh_tokens()",
				settings.trace, GLOBAL_LOG_WARN)                
			refresh_tokens()     
		}
		exceptionCheck=device.currentValue("verboseTrace")
		if (!statusCode) {
			/* when success, reset the exception counter */
			state.exceptionCount=0
			if ((data?."replayHoldId${state?.retriesHoldCounter}" == null) || 
				(state?.retriesHoldCounter > get_MAX_SETTER_RETRIES())) {          // reset the counter if last entry is null
				reset_replay_data('Hold')                
				state?.retriesHoldCounter=0
			}            
			traceEvent(settings.logFilter,"setHold>done for ${thermostatId}",settings.trace)
		} else {
			state.exceptionCount = state.exceptionCount +1     
			traceEvent(settings.logFilter,"setHold>> error=${statusCode.toString()},message=${message} for ${thermostatId}",true, GLOBAL_LOG_ERROR)
		} /* end if statusCode */
	} /* end api call */
	if (exceptionCheck?.contains("exception")) {
		sendEvent(name: "verboseTrace", value: "", displayed:(settings.trace?:false)) // reset verboseTrace            
		traceEvent(settings.logFilter,"setHold>exception=${exceptionCheck}", true, GLOBAL_LOG_ERROR)
		statusCode=(statusCode)?:3 //internal error            
	}                

	if ((statusCode) && (!exceptionCheck?.contains("Bad Request"))) {
		state?.retriesHoldCounter=(state?.retriesHoldCounter?:0)+1            
		if (!(interval=get_exception_interval_delay(state?.retriesHoldCounter))) {  
			traceEvent(settings.logFilter,"setHold>error max retries, counter=${state?.retriesHoldCounter}, exiting", true, GLOBAL_LOG_ERROR)
			reset_replay_data('Hold')                
			state?.retriesHoldCounter=0
			return        
		}        
		data?."replayHoldId${state?.retriesHoldCounter}"=thermostatId
		data?."replayCoolingSetpoint${state?.retriesHoldCounter}"=coolingSetPoint        
		data?."replayHeatingSetpoint${state?.retriesHoldCounter}"=heatingSetPoint 
		data?."replayFanMode${state?.retriesHoldCounter}"=fanMode        
		data?."replayHoldSettings${state?.retriesHoldCounter}"=tstatSettings    
		data?."replayExtraHoldParams${state?.retriesHoldCounter}"=extraHoldParams 
		traceEvent(settings.logFilter,"setHold>about to call setHoldReplay,interval=$interval, retries counter=${state?.retriesHoldCounter}", true, GLOBAL_LOG_INFO)
		runIn(interval, "setHoldReplay",[overwrite:true])        
	}    
    
}

void setHoldReplay() {
	def exceptionCheck=""
	for (int i=1; (i<= get_MAX_SETTER_RETRIES()); i++) {
		def id = data?."replayHoldId${i}"
		if (id == null) continue        
		def coolingSetpoint= data?."replayCoolingSetpoint${i}"        
		def heatingSetpoint= data?."replayHeatingSetpoint${i}"
		def fanMode=data?."replayFanMode${i}"        
		def tstatSettings=data?."replayHoldSettings${i}"
		def extraHoldParams= data?."replayExtraHoldParams${i}" 
		traceEvent(settings.logFilter,"setHoldReplay>about to recall setHold for thermostatId=$id,retries counter=$i" +
			",coolingSetpoint=$coolingSetpoint, heatingSetpoint=$heatingSetpoint,fanMode=$fanMode,tstatSettings=$tstatSettings,extraHoldParams=$extraHoldParams", true, GLOBAL_LOG_INFO)
		setHoldExtraParams(id, coolingSetpoint, heatingSetpoint, fanMode,
			tstatSettings, extraHoldParams)
		exceptionCheck=device.currentValue("verboseTrace")
		if (exceptionCheck?.contains("done")) {
			data?."replayHoldId$i"=null        
		} /* end if */
	} /* end for */
	if (exceptionCheck?.contains("done")) { // if last command executed OK, then reset the counter
		traceEvent(settings.logFilter,"setHoldReplay>resetting counter as last setHoldReplay command was ok..." +
			",tstatSettings=$tstatSettings", true, GLOBAL_LOG_INFO)
		state?.retriesHoldCounter=0
		reset_replay_data('Hold')                
	} /* end if */
}

private int find_delimited_substring(String stringVar, String substrVar, int posOrigin=0) {
	int position=stringVar.indexOf(substrVar + ':', posOrigin)
	position= (position != -1) ? position : stringVar.indexOf(substrVar + '=', posOrigin)  
	position= (position != -1) ? position : stringVar.indexOf(substrVar + ' ', posOrigin)  
	return position
}

private def extract_delimited_substring(String stringVar, int posOrigin=0) {
	def results

	int posDelim = stringVar.indexOf(',', posOrigin)
	posDelim = (posDelim!=-1) ? posDelim : stringVar.indexOf(';', posOrigin)
	posDelim = (posDelim!=-1) ? posDelim : stringVar.indexOf(' ', posOrigin)
	posDelim = (posDelim!=-1) ? posDelim : stringVar.length()
	traceEvent(settings.logFilter,"extract_delimited_string>stringVar=$stringVar, posOrigin=$posOrigin, posDelim=$posDelim",settings.trace)
	results = stringVar.substring(posOrigin,posDelim).trim()
	return results    
}	


// iterateCreateVacation: iterate thru all the thermostats under a specific account and create the vacation
// tstatType =managementSet or registered (no spaces).  
//	May also be set to a specific locationSet (ex./Toronto/Campus/BuildingA)
void iterateCreateVacation(tstatType, vacationName, targetCoolTemp,
	targetHeatTemp, targetStartDateTime, targetEndDateTime) {
	Integer MAX_TSTAT_BATCH = get_MAX_TSTAT_BATCH()
	def tstatlist = null
	Integer nTstats = 0
	def ecobeeType = determine_ecobee_type_or_location(tstatType)
	getThermostatSummary(ecobeeType)
	traceEvent(settings.logFilter,"iterateCreateVacation>ecobeeType=${ecobeeType},about to loop ${data.thermostatCount} thermostat(s)",settings.trace)
	for (i in 0..data.thermostatCount - 1) {
		def thermostatDetails = data.revisionList[i].split(':')
		def Id = thermostatDetails[0]
		def thermostatName = thermostatDetails[1]
		def connected = thermostatDetails[2]
		if (connected == 'true') {
			if (nTstats == 0) {
				tstatlist = Id
				nTstats = 1
			}
			if ((nTstats > MAX_TSTAT_BATCH) || (i == (data.thermostatCount - 1))) { 
				// process a batch of maximum 25 thermostats according to API doc
				traceEvent(settings.logFilter,"iterateCreateVacation>about to call createVacation for ${tstatlist}", settings.trace)
				createVacation("${tstatlist}", vacationName, targetCoolTemp, targetHeatTemp,
					targetStartDateTime, targetEndDateTime)
				tstatlist = Id
				nTstats = 1
			} else {
				tstatlist = tstatlist + "," + Id
				nTstats++ 
			}
		}
	}
}

// thermostatId may be a list of serial# separated by ",", no spaces (ex. '123456789012,123456789013') 
//	if no thermostatId is provided, it is defaulted to the current thermostatId 
void createVacation(thermostatId, vacationName, targetCoolTemp, targetHeatTemp,
	targetStartDateTime, targetEndDateTime) {    
	def ECOBEE_NEED_TOKEN_REFRESH=14
	def TOKEN_EXPIRED=401  
	def interval    
    
	thermostatId = determine_tstat_id(thermostatId)
	def vacationStartDate = String.format('%tY-%<tm-%<td', targetStartDateTime)
	def vacationStartTime = String.format('%tH:%<tM:%<tS', targetStartDateTime)
	def vacationEndDate = String.format('%tY-%<tm-%<td', targetEndDateTime)
	def vacationEndTime = String.format('%tH:%<tM:%<tS', targetEndDateTime)
	Integer targetCool = null,targetHeat = null

	def scale = state?.scale
	if (scale == 'C') {
		targetCool = (cToF(targetCoolTemp) * 10) as Integer // need to send temperature in F multiply by 10
		targetHeat = (cToF(targetHeatTemp) * 10) as Integer
	} else {
		targetCool = targetCoolTemp * 10 as Integer // need to send temperature in F multiply by 10
		targetHeat = targetHeatTemp * 10 as Integer
	}
	def vacationParams = [
		name: vacationName.trim(),
		coolHoldTemp: targetCool,
		heatHoldTemp: targetHeat,
		startDate: vacationStartDate,
		startTime: vacationStartTime,
		endDate: vacationEndDate,
		endTime: vacationEndTime
	]
	def bodyReq = build_body_request('createVacation',null,thermostatId,vacationParams)
	traceEvent(settings.logFilter,"createVacation> about to call api with body = ${bodyReq} for ${thermostatId} ", settings.trace)
	int statusCode=1
	int j=0        
	def exceptionCheck=""
	api('createVacation', bodyReq) {resp ->
		statusCode = resp?.data?.status?.code
		def message = resp?.data?.status?.message
		if ((resp?.status==TOKEN_EXPIRED) || ((resp?.status == 500) && (statusCode == ECOBEE_NEED_TOKEN_REFRESH))) {
			traceEvent(settings.logFilter,"createVacation>thermostatId=${thermostatId},error $statusCode, need to call refresh_tokens()",
				settings.trace, GLOBAL_LOG_WARN)                
			refresh_tokens()     
		}
		exceptionCheck=device.currentValue("verboseTrace")
		if (!statusCode) {
			/* when success, reset the exception counter */
			state.exceptionCount=0
			if ((data?."replayCrVacatationId${state?.retriesCreateVacationCounter}" == null) ||          
				(state?.retriesCreateVacationCounter > get_MAX_SETTER_RETRIES())) {          // reset the counter if last entry is null
				reset_replay_data('CrVacation')                
				state?.retriesCreateVacationCounter=0
			}            
			traceEvent(settings.logFilter,"createVacation>done for ${thermostatId}", settings.trace)
		} else {
			state.exceptionCount = state.exceptionCount +1     
			traceEvent(settings.logFilter,"createVacation>error=${statusCode.toString()},message=${message} for ${thermostatId}", true, GLOBAL_LOG_ERROR)
		}
	} /* end api */            
	if (exceptionCheck?.contains("exception")) {
		sendEvent(name: "verboseTrace", value: "", displayed:(settings.trace?:false)) // reset verboseTrace            
		traceEvent(settings.logFilter,"createVacation>exception=${exceptionCheck}, loop counter=$j", true, GLOBAL_LOG_ERROR)
		statusCode=(statusCode)?:3 //internal error            
	} 
	if ((statusCode) && (!exceptionCheck?.contains("Bad Request"))) {
		state?.retriesCreateVacationCounter=(state?.retriesCreateVacationCounter?:0)+1            
		if (!(interval=get_exception_interval_delay((state?.retriesCreateVacationCounter)))) {  
			traceEvent(settings.logFilter,"createVacation>error max retries, counter=${state?.retriesCreateVacationCounter}, exiting", true, GLOBAL_LOG_ERROR)
			reset_replay_data('CrVacation')                
			state?.retriesCreateVacationCounter=0
			return        
		}        
		data?."replayCrVacationId${state?.retriesCreateVacationCounter}"=thermostatId
		data?."replayCrVacationName${state?.retriesCreateVacationCounter}"=vacationName
		data?."replayTargetCoolTemp${state?.retriesCreateVacationCounter}"=targetCoolTemp
		data?."replayTargetHeatTemp${state?.retriesCreateVacationCounter}"=targetHeatTemp
		data?."replayTargetStartDateTime${state?.retriesCreateVacationCounter}"=targetStartDateTime
		data?."replayTargetStartEndTime${state?.retriesCreateVacationCounter}"=targetEndDateTime
		traceEvent(settings.logFilter,"createVacation>about to call createVacationReplay,retries counter=${state?.retriesCreateVacationCounter}", true, GLOBAL_LOG_INFO)
		runIn(interval, "createVacationReplay",[overwrite:true])        
	}    
    
}

void createVacationReplay() {
	def exceptionCheck=""
	for (int i=1; (i<= get_MAX_SETTER_RETRIES()); i++) {
		def id = data?.replayCrVacationId"${i}"
		if (id==null) continue        
		def vacationName=data?."replayCrVacationName${i}"
		def targetCoolTemp=data?."replayTargetCoolTemp${i}"
		def targetHeatTemp=data?."replayTargetHeatTemp${i}"
		def targetStartDateTime=data?."replayTargetStartDateTime${i}"
		def targetEndDateTime=data?."replayTargetEndDateTime${i}"
		traceEvent(settings.logFilter,"createVacationReplay>about to call createVacation,retries counter=$i" +
			",vacationName=$vacationName,targetCoolTemp=$targetCoolTemp,targetHeatTemp=targetHeatTemp", true, GLOBAL_LOG_INFO)
		createVacation(id, vacationName, targetCoolTemp, targetHeatTemp,
			targetStartDateTime, targetEndDateTime)          
		exceptionCheck=device.currentValue("verboseTrace")
		if (exceptionCheck?.contains("done")) {
			data?."replayCrVacationId$i"=null        
		} /* end if */
	} /* end for */
	if (exceptionCheck?.contains("done")) { // if last command executed OK, then reset the counter
		state?.retriesCreateVacationCounter=0
		reset_replay_data('CrVacation')                
	} /* end if */
}

// iterateDeleteVacation: iterate thru all the thermostats under a specific account and delete the vacation
// tstatType =managementSet or registered (no spaces).  
//	May also be set to a specific locationSet (ex./Toronto/Campus/BuildingA)
void iterateDeleteVacation(tstatType, vacationName) {
	Integer MAX_TSTAT_BATCH = get_MAX_TSTAT_BATCH()
	def tstatlist = null
	Integer nTstats = 0
    
	def ecobeeType = determine_ecobee_type_or_location(tstatType)
	getThermostatSummary(ecobeeType)
	traceEvent(settings.logFilter,"iterateDeleteVacation>ecobeeType=${ecobeeType},about to loop ${data.thermostatCount} thermostat(s)", settings.trace)
	for (i in 0..data.thermostatCount - 1) {
		def thermostatDetails = data.revisionList[i].split(':')
		def Id = thermostatDetails[0]
		def thermostatName = thermostatDetails[1]
		def connected = thermostatDetails[2]
		if (connected == 'true') {
			if (nTstats == 0) {
				tstatlist = Id
				nTstats = 1
			}
			if ((nTstats > MAX_TSTAT_BATCH) || (i == (data.thermostatCount - 1))) { 
				// process a batch of maximum 25 thermostats according to API doc
				traceEvent(settings.logFilter,"iterateDeleteVacation> about to call deleteVacation for ${tstatlist}", settings.trace)
				deleteVacation("${tstatlist}", vacationName)
				tstatlist = Id
				nTstats = 1
			} else {
				tstatlist = tstatlist + "," + Id
				nTstats++ 
			}
		}
	}
}



// thermostatId may be a list of serial# separated by ",", no spaces (ex. '123456789012,123456789013') 
//	if no thermostatId is provided, it is defaulted to the current thermostatId 
void deleteVacation(thermostatId, vacationName) {
	def ECOBEE_NEED_TOKEN_REFRESH=14
	def TOKEN_EXPIRED=401    
	def interval 
  
	thermostatId = determine_tstat_id(thermostatId)
	def vacationParams = [name: vacationName.trim()]
	def bodyReq = build_body_request('deleteVacation',null,thermostatId,vacationParams)
    
	int statusCode=1
	def exceptionCheck    
	api('deleteVacation', bodyReq) {resp ->
		statusCode = resp?.data?.status?.code
		def message = resp?.data?.status?.message
		if ((resp?.status==TOKEN_EXPIRED) || ((resp?.status == 500) && (statusCode == ECOBEE_NEED_TOKEN_REFRESH))) {
			traceEvent(settings.logFilter,"deleteVacation>thermostatId=${thermostatId},error $statusCode, need to call refresh_tokens()", settings.trace, GLOBAL_LOG_WARN)
			refresh_tokens()     
		}
		exceptionCheck=device.currentValue("verboseTrace")
		if (!statusCode) {
			/* when success, reset the exception counter */
			state.exceptionCount=0
			if ((data."replayDelVacatationId${state?.retriesDeleteVacationCounter}" == null) ||      
				(state?.retriesDeleteVacationCounter > get_MAX_SETTER_RETRIES())) {          // reset the counter if last entry is null
				reset_replay_data('DelVacation')                
				state?.retriesDeleteVacationCounter=0
			}            
			traceEvent(settings.logFilter,"deleteVacation>done for ${thermostatId}", settings.trace)
		} else {
			state.exceptionCount = state.exceptionCount +1     
			traceEvent(settings.logFilter,"deleteVacation>error ${statusCode.toString()},message=${message} for ${thermostatId}", true, GLOBAL_LOG_ERROR)
		}                    
	} /* end api */
	if (exceptionCheck?.contains("exception")) {
		sendEvent(name: "verboseTrace", value: "", displayed:(settings.trace?:false)) // reset verboseTrace            
		traceEvent(settings.logFilter,"deleteVacation>exception=${exceptionCheck}, loop counter=$j", true, GLOBAL_LOG_ERROR)
		statusCode=(statusCode)?:3 //internal error            
	}                
	if ((statusCode) && (!exceptionCheck?.contains("Bad Request"))) {
		state?.retriesDeleteVacationCounter=(state?.retriesDeleteVacationCounter?:0)+1            
		if (!(interval=get_exception_interval_delay(state?.retriesDeleteVacationCounter))) {  
			traceEvent(settings.logFilter,"deleteVacation>error max retries, counter=${state?.retriesDeleteVacationCounter}, exiting", true, GLOBAL_LOG_ERROR)
			reset_replay_data('DelVacation')                
			state?.retriesDeleteVacationCounter=0
			return        
		}        
		data?."replayDelVacatationId${state?.retriesDeleteVacationCounter}"=thermostatId
		data?."replayDelVacationName${state?.retriesDeleteVacationCounter}"=vacationName
		traceEvent(settings.logFilter,"deleteVacation>about to call createVacationReplay,retries counter=${state?.retriesDeleteVacationCounter}", true, GLOBAL_LOG_INFO)
		runIn(interval, "deleteVacationReplay",[overwrite:true])        
	}    
    
}

void deleteVacationReplay() {
	def exceptionCheck=""
	for (int i=1; (i<= get_MAX_SETTER_RETRIES()); i++) {
		def id = data?."replayDelVacationId$i"
		if (id==null) continue        
		def vacationName=data?."replayDelVacationName$i"
		traceEvent(settings.logFilter,"deleteVacationReplay>about to call deleteVacation,retries counter=$i" +
			",vacationName=$vacationName", true, GLOBAL_LOG_INFO)
		deleteVacation(id, vacationName)        
		exceptionCheck=device.currentValue("verboseTrace")
		if (exceptionCheck?.contains("done")) {
			data?."replayDelVacationId$i"=null        
		} /* end if */
	} /* end for */
	if (exceptionCheck?.contains("done")) { // if last command executed OK, then reset the counter
		reset_replay_data('DelVacation')                
		state?.retriesDeleteVacationCounter=0
	} /* end if */
}
// tstatType =managementSet or registered (no spaces).  May also be set to a specific locationSet (ex./Toronto/Campus/BuildingA)
// iterateResumeProgram: iterate thru all the thermostats under a specific account and resume their program
void iterateResumeProgram(tstatType) {
	Integer MAX_TSTAT_BATCH = get_MAX_TSTAT_BATCH()
	def tstatlist = null
	Integer nTstats = 0

	def ecobeeType = determine_ecobee_type_or_location(tstatType)
	getThermostatSummary(ecobeeType)
	traceEvent(settings.logFilter,"iterateResumeProgram>ecobeeType=${ecobeeType},about to loop ${data.thermostatCount} thermostat(s)", settings.trace)
	for (i in 0..data.thermostatCount - 1) {
		def thermostatDetails = data.revisionList[i].split(':')
		def Id = thermostatDetails[0]
		def thermostatName = thermostatDetails[1]
		def connected = thermostatDetails[2]
		if (connected == 'true') {
			if (nTstats == 0) {
				tstatlist = Id
				nTstats = 1
			}
			if ((nTstats > MAX_TSTAT_BATCH) || (i == (data.thermostatCount - 1))) { // process a batch of maximum 25 thermostats according to API doc
				traceEvent(settings.logFilter,"iterateResumeProgram> about to call resumeProgram for ${tstatlist}", settings.trace)
				resumeProgram("${tstatlist}")
				tstatlist = Id
				nTstats = 1
			} else {
				tstatlist = tstatlist + "," + Id
				nTstats++ 
			}
		}
	}
}

// thermostatId may be a list of serial# separated by ",", no spaces (ex. '123456789012,123456789013') 
// 	if no thermostatId is provided, it is defaulted to the current thermostatId 
// resumeAllFlag, if true then a resume all will be sent, otherwise, just a partial resume will be done.
void resumeProgram(thermostatId=settings.thermostatId, resumeAllFlag=true) {  
	thermostatId = determine_tstat_id(thermostatId)
	def ECOBEE_NEED_TOKEN_REFRESH=14
	def TOKEN_EXPIRED=401   
	def interval    

	def resumeParams=null
    
 	if (resumeAllFlag==true) {
		resumeParams = [resumeAll: 'true']
	}
    
	def bodyReq = build_body_request('resumeProgram',null,thermostatId,resumeParams)
	traceEvent(settings.logFilter,"resumeProgram> about to call api with body = ${bodyReq} for ${thermostatId}", settings.trace)
	int statusCode=1
	def exceptionCheck    
	api('resumeProgram', bodyReq) {resp ->
		statusCode = resp?.data?.status?.code
		def message = resp?.data?.status?.message
		if ((resp?.status==TOKEN_EXPIRED) || ((resp?.status == 500) && (statusCode == ECOBEE_NEED_TOKEN_REFRESH))) {
			traceEvent(settings.logFilter,"resumeProgram>thermostatId=${thermostatId},error $statusCode, need to call refresh_tokens()",settings.trace,
				GLOBAL_LOG_WARN)                    
			refresh_tokens()     
		}
		exceptionCheck=device.currentValue("verboseTrace")
		if (!statusCode) {
			/* when success, reset the exception counter */
			state.exceptionCount=0
			if ((data?."replayResumeId${state?.retriesResumeCounter}"==null) ||
				(state?.retriesResumeCounter > get_MAX_SETTER_RETRIES())) {          // reset the counter if last entry is null
				state?.retriesResumeCounter=0					            
				reset_replay_data('Resume')                
			}                    
			traceEvent(settings.logFilter,"resumeProgram>resume done for ${thermostatId}", settings.trace)
		} else {
			state.exceptionCount = state.exceptionCount +1     
			traceEvent(settings.logFilter,"resumeProgram>error=${statusCode.toString()},message=${message} for ${thermostatId}",true, GLOBAL_LOG_ERROR)
		}                    
	} /* end api */
	if (exceptionCheck?.contains("exception")) {
		sendEvent(name: "verboseTrace", value: "", displayed:(settings.trace?:false)) // reset verboseTrace            
		traceEvent(settings.logFilter,"resumeProgram>exception=${exceptionCheck}", true, GLOBAL_LOG_ERROR)
		statusCode=(statusCode)?:3 //internal error            
	}                

	if ((statusCode) && (!exceptionCheck?.contains("Bad Request"))) {
		state?.retriesResumeCounter=(state?.retriesResumeCounter?:0)+1            
		if (!(interval=get_exception_interval_delay(retriesResumeCounter))) {  
			traceEvent(settings.logFilter,"resumeProgram>error max retries, counter=${state?.retriesResumeCounter}, exiting", true, GLOBAL_LOG_ERROR)
			state?.retriesResumeCounter=0					            
			reset_replay_data('Resume')                
			return        
		}        
		data?."replayResumeId${state?.retriesResumeCounter}"=thermostatId
		data?."replayResumeAllFlag${state?.retriesResumeCounter}"=resumeAllFlag        
		traceEvent(settings.logFilter,"resumeProgram>about to call resumeProgramReplay,retries counter=${state?.retriesResumeCounter}, interval=$interval", true, GLOBAL_LOG_INFO)
		runIn(interval, "resumeProgramReplay",[overwrite:true])        
	}    
    
}

void resumeProgramReplay() {
	def exceptionCheck=""
	for (int i=1; (i<= get_MAX_SETTER_RETRIES()); i++) {
		def id = data?."replayResumeId$i"
		if (id==null) continue        
		def resumeAllFlag=data?."replayResumeAllFlag$i"    
		traceEvent(settings.logFilter,"resumeProgramReplay>about to recall resumeProgram for thermostatId=$id,retries counter=${state?.retriesResumeCounter}",
			true, GLOBAL_LOG_INFO)
		resumeProgram(id, resumeAllFlag)
		exceptionCheck=device.currentValue("verboseTrace")
		if (exceptionCheck?.contains("done")) {
			data?."replayResumeId$i"=null        
		} /* end if */
	} /* end for */
	if (exceptionCheck?.contains("done")) { // if last command executed OK, then reset the counter
		reset_replay_data('Resume')                
		state?.retriesResumeCounter=0
	} /* end if */
}
// Only valid for Smart and ecobee3 and beyond thermostats (not for EMS)
// Get all groups related to a thermostatId or all groups
// thermostatId may only be 1 thermostat (not a list) or null (for all groups)
void getGroups(thermostatId) {
	def ECOBEE_NEED_TOKEN_REFRESH=14
	def TOKEN_EXPIRED=401    
    
	thermostatId = determine_tstat_id(thermostatId)
	def ecobeeType = determine_ecobee_type_or_location("")
	traceEvent(settings.logFilter,"getGroups>ecobee Type = $ecobeeType for thermostatId = $thermostatId",settings.trace)
	if (ecobeeType.toUpperCase() != 'REGISTERED') {
		traceEvent(settings.logFilter,"getGroups>managementSet is not a valid settings.ecobeeType for getGroups",settings.trace, GLOBAL_LOG_WARN)
		data.groups = null
		return
	}
	def bodyReq = '{"selection":{"selectionType":"registered"}}'
	api('getGroups', bodyReq) {resp ->
		statusCode = resp?.data?.status?.code
		def message = resp?.data?.status?.message
		if ((resp?.status==TOKEN_EXPIRED) || ((resp?.status == 500) && (statusCode == ECOBEE_NEED_TOKEN_REFRESH))) {
			traceEvent(settings.logFilter,"getGroups>thermostatId=${thermostatId},error $statusCode, need to call refresh_tokens()", settings.trace, GLOBAL_LOG_WARN)
			refresh_tokens()     
		}
		def exceptionCheck=device.currentValue("verboseTrace")
		if (exceptionCheck.contains("exception")) {
			statusCode=(statusCode)?statusCode:3
		}
		if (!statusCode) {
			/* when success, reset the exception counter */
			state.exceptionCount=0
			data.groups = resp.data.groups
			traceEvent(settings.logFilter,"getGroups>done for ${thermostatId},group size = ${data.groups.size()}, groups data = ${data.groups}", settings.trace)
			if (data.groups.size() == 0) {
				return
			}
			if ((thermostatId != null) && (thermostatId != "")) {
				if (data.groups.thermostats.size() > 0) {
					for (i in 0..data.groups.size() - 1) {
						def foundTstat = false
						for (j in 0..data.groups[i].thermostats.size() - 1) {
							if (data.groups[i].thermostats[j] == thermostatId) {
								traceEvent(settings.logFilter,"getGroups>found group ${data.groups[i]} for thermostatId= ${thermostatId}", settings.trace)
								foundTstat=true
							}
						}   
						if (!foundTstat) {
							data.groups[i].groupName = '' // Not the right group for this thermostat, set it to blanks
						}    
					}
				}
			}
		} else {
			state.exceptionCount = state.exceptionCount +1     
			sendEvent(name: "verboseTrace", value: "", displayed:(settings.trace?:false)) // reset verboseTrace            
			traceEvent(settings.logFilter,"getGroups>error ${statusCode.toString()},message=${message} for ${thermostatId}", true, GLOBAL_LOG_ERROR)
		}
	}
}

// Only valid for Smart and ecobee3 and beyond thermostats (not for EMS)
// iterateUpdateGroup: iterate thru all the Groups under a specific account and update their settings
// Get all groups related to a thermostatId and update them with the groupSettings
// thermostatId may only be 1 thermostat (not a list), if null or empty, then defaulted to this thermostatId (settings)
// 	if no thermostatId is provided, it is defaulted to the current thermostatId 
// groupSettings may be a map of settings separated by ",", no spaces; 
// 	For more details, see https://beta.ecobee.com/home/developer/api/documentation/v1/objects/Group.shtml
void iterateUpdateGroup(thermostatId, groupSettings = []) {
	thermostatId = determine_tstat_id(thermostatId)
	getGroups(thermostatId)
	traceEvent(settings.logFilter,"iterateUpdateGroup> about to loop ${data.groups.size()}", settings.trace)
	if (data.groups.size() == 0) {
		return
	}
	for (i in 0..data.groups.size() - 1) {
		def groupName = data.groups[i].groupName
		def groupRef = data.groups[i].groupRef
		def synchronizeSchedule = data.groups[i].synchronizeSchedule
		def synchronizeVacation = data.groups[i].synchronizeVacation
		def synchronizeSystemMode = data.groups[i].synchronizeSystemMode
		traceEvent(settings.logFilter,"iterateUpdateGroup> about to call updateGroup for ${groupName}, groupRef= ${groupRef}," +
				"synchronizeSystemMode=${synchronizeSystemMode}, synchronizeVacation=${synchronizeVacation}" +
				"synchronizeSchedule=${synchronizeSchedule}", settings.trace)
		updateGroup(groupRef, groupName, thermostatId, groupSettings)
		traceEvent(settings.logFilter,"iterateUpdateGroup>done for groupName = ${groupName}, groupRef= ${groupRef}",settings.trace)
	}
}

// Only valid for Smart and ecobee3 and beyond thermostats (not for EMS)
// If groupRef is not provided, it is assumed that a group creation needs to be done
// thermostatId may be a list of serial# separated by ",", no spaces (ex. '123456789012,123456789013') 
//	if no thermostatId is provided, it is defaulted to the current thermostatId 
// groupSettings could be a map of settings separated by ",", no spaces; 
//	For more details, see https://beta.ecobee.com/home/developer/api/documentation/v1/objects/Group.shtml
void updateGroup(groupRef, groupName, thermostatId, groupSettings = []) {
	String updateGroupParams
	def groupSettingsJson = new groovy.json.JsonBuilder(groupSettings)
	def groupSet = groupSettingsJson.toString().minus('{').minus('}')
	def ECOBEE_NEED_TOKEN_REFRESH=14
	def TOKEN_EXPIRED=401    
 

	thermostatId = determine_tstat_id(thermostatId)
	traceEvent(settings.logFilter,"updateGroup> about to assemble bodyReq for groupName =${groupName}, thermostatId = ${thermostatId}, groupSettings=${groupSet}", settings.trace)
	if ((groupRef != null) && (groupRef.trim() != "")) {

		updateGroupParams = '"groupRef":"' + groupRef.trim() + '","groupName":"' +
			groupName.trim() +
			'",' + groupSet + ',"thermostats":["' + thermostatId + '"]'
	} else {
		updateGroupParams = '"groupName":"' + groupName.trim() + '",' + groupSet +
			',"thermostats":["' + thermostatId + '"]'
	}
	def bodyReq = '{"selection":{"selectionType":"registered"},"groups":[{' +
		updateGroupParams + '}]}'
	traceEvent(settings.logFilter,"updateGroup> about to call api with body = ${bodyReq} for groupName =${groupName},thermostatId = ${thermostatId}...", settings.trace)
	int statusCode=1
	int j=0        
	while ((statusCode) && (j++ <2)) { // retries once if api call fails
		def exceptionCheck
		api('updateGroup', bodyReq) {resp ->
			statusCode = resp?.data?.status?.code
			def message = resp?.data?.status?.message
			if ((resp?.status==TOKEN_EXPIRED) || ((resp?.status == 500) && (statusCode == ECOBEE_NEED_TOKEN_REFRESH))) {
				traceEvent(settings.logFilter,"updateGroup>thermostatId=${thermostatId},error $statusCode, need to call refresh_tokens()", settings.trace, GLOBAL_LOG_WARN)
				refresh_tokens()     
			}
			exceptionCheck=device.currentValue("verboseTrace")
			if (!statusCode) {
				/* when success, reset the exception counter */
				state.exceptionCount=0
				traceEvent(settings.logFilter,"updateGroup>done for groupName =${groupName}, ${thermostatId}", settings.trace)
			} else {
				state.exceptionCount = state.exceptionCount +1     
				traceEvent(settings.logFilter,"updateGroup>error ${statusCode.toString()},message=${message} for ${thermostatId}", true, GLOBAL_LOG_ERROR)
			} /* end if statusCode */
		} /* end api call */                
		if (exceptionCheck?.contains("exception")) {
			sendEvent(name: "verboseTrace", value: "", displayed:(settings.trace?:false)) // reset verboseTrace            
			traceEvent(settings.logFilter,"updateGroup>exception=${exceptionCheck}, loop counter=$j", true, GLOBAL_LOG_ERROR)
			statusCode=(statusCode)?:3 //internal error            
		}                
	} /* end while */
}

// Only valid for Smart and ecobee3 and beyond thermostats (not for EMS)
// For more details, see https://beta.ecobee.com/home/developer/api/documentation/v1/objects/Group.shtml
void deleteGroup(groupRef, groupName) {
	String updateGroupParams
	def ECOBEE_NEED_TOKEN_REFRESH=14
	def TOKEN_EXPIRED=401    
    
	if ((groupRef != null) && (groupRef.trim() != "")) {
		updateGroupParams = '"groupRef":"' + groupRef.trim() + '","groupName":"' +
			groupName.trim() + '"'
	} else {
		updateGroupParams = '"groupName":"' + groupName.trim() + '"'
	}
	traceEvent(settings.logFilter,"deleteGroup> updateGroupParams=${updateGroupParams}",settings.trace)
	def bodyReq = '{"selection":{"selectionType":"registered"},"groups":[{' +
		updateGroupParams + '}]}'
	traceEvent(settings.logFilter,"deleteGroup> about to call api with body = ${bodyReq} for groupName =${groupName}, groupRef = ${groupRef}", settings.trace)
	int statusCode=1
	int j=0        
	while ((statusCode) && (j++ <2)) { // retries once if api call fails
		def exceptionCheck
		api('updateGroup', bodyReq) {resp ->
			statusCode = resp?.data?.status?.code
			def message = resp?.data?.status?.message
			if ((resp?.status==TOKEN_EXPIRED) || ((resp?.status == 500) && (statusCode == ECOBEE_NEED_TOKEN_REFRESH))) {
				traceEvent(settings.logFilter,"deleteGroup>thermostatId=${thermostatId},error $statusCode, need to call refresh_tokens()",settings.trace, GLOBAL_LOG_WARN)
				refresh_tokens()     
			}
			exceptionCheck=device.currentValue("verboseTrace")
			if (!statusCode) {
				/* when success, reset the exception counter */
				state.exceptionCount=0
				traceEvent(settings.logFilter,"deleteGroup>done for groupName =${groupName},groupRef = ${groupRef}", settings.trace)
			} else {
				state.exceptionCount = state.exceptionCount +1     
				traceEvent(settings.logFilter,"deteteGroup>error ${statusCode.toString()},message= ${message} for ${groupName},groupRef= ${groupRef}", true, GLOBAL_LOG_ERROR)
			}                    
		} /* end api */
		if (exceptionCheck?.contains("exception")) {
			sendEvent(name: "verboseTrace", value: "", displayed:(settings.trace?:false)) // reset verboseTrace            
			traceEvent(settings.logFilter,"deleteGroup>exception=${exceptionCheck}, loop counter=$j", true, GLOBAL_LOG_ERROR)
			statusCode=(statusCode)?:3 //internal error            
		}                
	} /* end while */
}

// Only valid for Smart and ecobee3 and beyond thermostats (not for EMS)
// thermostatId may be a list of serial# separated by ",", no spaces (ex. '123456789012,123456789013') 
//	if no thermostatId is provided, it is defaulted to the current thermostatId 
// groupSettings could be a map of settings separated by ",", no spaces; 
// For more details, see https://beta.ecobee.com/home/developer/api/documentation/v1/objects/Group.shtml
void createGroup(groupName, thermostatId, groupSettings = []) {
	updateGroup(null, groupName, thermostatId, groupSettings)
}

// thermostatId may only be 1 thermostat (not a list) 
// climate name is the name of the climate to be created (ex. "Bedtime").
// isOptimized is 'true' or 'false'
// isOccupied is 'true' or 'false'
// coolFan & heatFan's mode is 'auto' or 'on'
void createClimate(thermostatId, climateName, coolTemp, heatTemp, isOptimized, isOccupied,
	coolFan, heatFan) {
    
	updateClimate(thermostatId, climateName, 'false', null, coolTemp, heatTemp,
		isOptimized, isOccupied, coolFan, heatFan)
}

// thermostatId can be only 1 thermostat (not a list) 
// climate name is the name of the climate to be deleted (ex. "Bedtime").
void deleteClimate(thermostatId, climateName, substituteClimateName) {
	updateClimate(thermostatId, climateName, 'true', substituteClimateName, null,
		null, null, null, null, null)
}
// iterateSetClimate: iterate thru all the thermostats under a specific account and set their Climate
// tstatType =managementSet or registered (no spaces).  May also be set to a specific locationSet (ex./Toronto/Campus/BuildingA)
// climate name is the name of the climate bet set to(ex. "Home", "Away").

void iterateSetClimate(tstatType, climateName) {
	Integer MAX_TSTAT_BATCH = 25
	def tstatlist = null
	Integer nTstats = 0
	def ecobeeType = determine_ecobee_type(tsatType)
	getThermostatSummary(ecobeeType)
	traceEvent(settings.logFilter,"iterateSetClimate>ecobeeType=${ecobeeType},about to loop ${data.thermostatCount} thermostat(s)", settings.trace)
	for (i in 0..data.thermostatCount - 1) {
		def thermostatDetails = data.revisionList[i].split(':')
		def Id = thermostatDetails[0]
		def thermostatName = thermostatDetails[1]
		def connected = thermostatDetails[2]
		if (connected == 'true') {
			if (nTstats == 0) {
				tstatlist = Id
				nTstats = 1
			}
			if ((nTstats > MAX_TSTAT_BATCH) || (i == (data.thermostatCount - 1))) { 
				// process a batch of maximum 25 thermostats according to API doc
				setClimate("${tstatlist}", climateName, sensors)
				tstatlist = Id
				nTstats = 1
			} else {
				tstatlist = tstatlist + "," + Id
				nTstats++ 
			}
		}
	}
}

// thermostatId may be a list of serial# separated by ",", no spaces (ex. '"123456789012","123456789013"') 
// climateName is the climateName as defined at ecobee (Away, Home, Sleep, custom ones).
// holdType as a String can be 'indefinite', 'nextTransition', 'holdHours', 'dateTime': any holdType supported by ecobee at https://www.ecobee.com/home/developer/api/documentation/v1/functions/SetHold.shtml
// holdHours (optional) as an integer as indicated at https://www.ecobee.com/home/developer/api/documentation/v1/functions/SetHold.shtml, required for 'holdHOurs' holdType
// startDate (optional) as a String as indicated at https://www.ecobee.com/home/developer/api/documentation/v1/functions/SetHold.shtml required for 'dateTime' holdType, format: 'YYYY-MM-DD'
// startTime (optional)as a String as indicated at https://www.ecobee.com/home/developer/api/documentation/v1/functions/SetHold.shtml, format 'HH:MI:SS', default: '00:00:00'
// endDate (optional) as a String as indicated at https://www.ecobee.com/home/developer/api/documentation/v1/functions/SetHold.shtml, format: 'YYYY-MM-DD', default: '2035-01-01'
// endTime (optional) as a String as indicated at https://www.ecobee.com/home/developer/api/documentation/v1/functions/SetHold.shtml, format 'HH:MI:SS', default: '23:59:59'

// example1: device.setClimateWithHoldType('','Away','indefinite')		// to set an indefinite Away hold on current thermostat
// example2: device.setClimateWithHoldType('','Away','holdHours',2)	// to set an Away holdHours of 2 hours on current thermostat
// example3: device.setClimateWithHoldType('','Away','dateTime', null,'2018-07-11','16:28:00') // to set a "dateTime" Away holdType starting on 2018-07-28 at 16:28 and ending in 2035-01-01


void setClimateWithHoldType(thermostatId, climateName,
	holdType, holdHours=null, startDate=null, startTime=null, endDate=null, endTime=null) {

	thermostatId = determine_tstat_id(thermostatId)
	traceEvent(settings.logFilter,"setClimateWithHoldType>called with values holdType=$holdType, climateName=$climateName for ${thermostatId}", settings.trace)
	def uppercaseHoldType=holdType.trim().toUpperCase()    
	if (uppercaseHoldType.contains("NEXTTRANSITION")) { // to avoid any issue with the wrong case in the preference parameter.
		holdType="nextTransition"			        
	} else if (uppercaseHoldType.contains("INDEFINITE")) {
		holdType="indefinite"			        
	} else if (uppercaseHoldType.contains("HOLDHOURS")) {
		holdType="holdHours"            
	} else if (uppercaseHoldType.contains("DATETIME")) {
		holdType="dateTime"            
	} 
	if ((holdType == 'dateTime') && (startDate==null || startDate=="")) {
		traceEvent(settings.logFilter,"setClimateWithHoldType>error, the holdType $holdType requires startDate param to bet set for ${thermostatId}",true, GLOBAL_LOG_ERROR)
		return    
	} 
	if ((holdType == 'holdHours') && ((holdHours==null  || ((!holdHours.isNumber())) || holdHours <=0))) {
		traceEvent(settings.logFilter,"setClimateWithHoldType>error, the holdType $holdType requires holdHours param to bet set with an integer value greater than 0 for ${thermostatId}",true, GLOBAL_LOG_ERROR)
		return    
	} 
		    
    // Determine the right hold Params
    
	def holdParams=[:]

	if (holdType in ['nextTransition', 'indefinite']) {    
		holdParams=["holdType": "${holdType}"]
	} else if (holdType == 'holdHours') {
		holdParams= [holdType: "${holdType}", holdHours: holdHours]
	} else if (holdType == 'dateTime') {
		if (startTime == null || startTime =="") {
			startTime="00:00:00"                
		}                
		if (endDate == null || endDate == "") {
			endDate="2035-01-01"                
		}                
		if (endTime == null || endTime == "") {
			endTime="23:59:59"                
		}                
		holdParams= [holdType:holdType,startDate:startDate,startTime:startTime,endDate:endDate,endTime:endTime]
	}  
    
	setClimate(thermostatId, climateName, holdParams)    

}
// thermostatId may be a list of serial# separated by ",", no spaces (ex. '123456789012,123456789013') 
//	if no thermostatId is provided, it is defaulted to the current thermostatId 
//	The settings.thermostatId (input) is the default one
// climate name is the name of the climate to be set to (ex. "Home", "Away").
// paramsMap: parameters Map, ex. [holdType:'nextTransition']  
//	see https://www.ecobee.com/home/developer/api/documentation/v1/functions/SetHold.shtml 

void setClimate(thermostatId, climateName, paramsMap=[]) {
	def climateRef = null
	def tstatParams,holdType, holdHours,startDate,startTime,endDate,endTime
	def ECOBEE_NEED_TOKEN_REFRESH=14
	def TOKEN_EXPIRED=401   
	def interval    
    
	if (thermostatId) {
//		call getThermostatInfo if a value for thermostatId is provided to make sure to have the right thermostat information

		getThermostatInfo(thermostatId) 
	}   
	thermostatId = determine_tstat_id(thermostatId)
	for (i in 0..data.thermostatList.size() - 1) {
		def foundClimate = data.thermostatList[i].program.climates.find{ it.name.toUpperCase() == climateName.toUpperCase() }
		if (foundClimate) {
			climateRef = foundClimate.climateRef
			traceEvent(settings.logFilter,"setClimate>Climate ${climateRef} found for thermostatId =${data.thermostatList[i].identifier}",settings.trace)
		} else {
			traceEvent(settings.logFilter,"setClimate>Climate ${climateName} not found for thermostatId =${data.thermostatList[i].identifier}",settings.trace, GLOBAL_LOG_WARN)
			continue
		}
        
		if (((settings.holdType != null) && (settings.holdType.trim() != "")) && 
			((!paramsMap) || ((paramsMap) && (!(paramsMap.find{ name, value-> name.toUpperCase() == 'HOLDTYPE'}))))) {
			def uppercaseHoldType=settings.holdType.trim().toUpperCase()    
			if (uppercaseHoldType.contains("NEXTTRANSITION")) { // to avoid any issue with the wrong case in the preference parameter.
				holdType="nextTransition"			        
			} else if (uppercaseHoldType.contains("INDEFINITE")) {
				holdType="indefinite"			        
			} else if (uppercaseHoldType.contains("HOLDHOURS")) {
				holdType="holdHours"            
				int posHoldHours=find_delimited_substring(uppercaseHoldType,'HOLDHOURS')
				holdHours=extract_delimited_substring(uppercaseHoldType,(posHoldHours+10))
				holdHours= (holdHours) ? holdHours.toInteger() :1 // by default, holdHours=1            
			} else if (uppercaseHoldType.contains("STARTDATE")) {
				holdType="dateTime"            
				int posStartDate=find_delimited_substring(uppercaseHoldType,'STARTDATE')
				int posStartTime=find_delimited_substring(uppercaseHoldType,'STARTTIME')
				int posEndDate=find_delimited_substring(uppercaseHoldType,'ENDDATE')                       
				int posEndTime=find_delimited_substring(uppercaseHoldType,'ENDTIME')                       
				if (posStartDate != -1) {
					startDate=extract_delimited_substring(uppercaseHoldType,(posStartDate+10))
				}        
				if (posStartTime != -1) {
					startTime=extract_delimited_substring(uppercaseHoldType,(posStartTime+10))
				} else {
					startTime="00:00:00"                
				}                
				if (posEndDate != -1) {
					endDate= extract_delimited_substring(uppercaseHoldType,(posEndDate+8))
				} else {
					endDate="2035-01-01"                
				}                
				if (posEndTime != -1) {
					endTime=extract_delimited_substring(uppercaseHoldType,(posEndTime+8))
				} else {
					endTime="23:59:59"                
				}                
			}        
			tstatParams = [holdClimateRef:"${climateRef}", holdType:"${holdType}"]
			if (holdType == "holdHours") {             
				tstatParams=tstatParams + [holdHours:holdHours]        
			}        
			if ((holdType == "dateTime") && (startDate && startTime && endDate && endTime)) {             
				tstatParams=tstatParams + ['startDate':startDate, 'startTime':startTime, 'endDate':endDate, 'endTime':endTime]                       
			}        
		} else {
			tstatParams= [holdClimateRef:"${climateRef}"]
		}  
		tstatParams = tstatParams + paramsMap            
		def bodyReq = build_body_request('setHold',null, data.thermostatList[i].identifier,tstatParams)	
		int statusCode=1
		def exceptionCheck
		api('setHold', bodyReq) {resp ->
			statusCode = resp?.data?.status?.code
			def message = resp?.data?.status?.message
			if ((resp?.status==TOKEN_EXPIRED) || ((resp?.status == 500) && (statusCode == ECOBEE_NEED_TOKEN_REFRESH))) {
				traceEvent(settings.logFilter,"setClimate>thermostatId=${data.thermostatList[i].identifier},error $statusCode, need to call refresh_tokens()",settings.trace, GLOBAL_LOG_WARN)
				refresh_tokens()     
			}
			exceptionCheck=device.currentValue("verboseTrace")
			if (!statusCode) {
				/* when success, reset the exception counter */
				state.exceptionCount=0
				if ((data?."replayClimateId${state?.retriesClimateCounter}" == null) ||
					(state?.state?.retriesClimateCounter > get_MAX_SETTER_RETRIES())) {          // reset the counter if last entry is null
					state?.retriesClimateCounter=0
					reset_replay_data('Climate')                
				}                
				/* Post the setClimate value */    
				sendEvent(name: 'setClimate', value: climateName, isStateChange:true)
				traceEvent(settings.logFilter,"setClimate>done for thermostatId =${data.thermostatList[i].identifier},climateName =${climateName}",settings.trace)
			} else {
				state.exceptionCount = state.exceptionCount +1     
				traceEvent(settings.logFilter,"setClimate>error ${statusCode.toString()},message=${message} while setting climate ${climateName} for thermostatId =${data.thermostatList[i].identifier}",
					true,  GLOBAL_LOG_ERROR)
			} /* end if statusCode */
		} /* end api call */                   
		if (exceptionCheck?.contains("exception")) {
			sendEvent(name: "verboseTrace", value: "", displayed:(settings.trace?:false)) // reset verboseTrace            
			traceEvent(settings.logFilter,"setClimate>exception=${exceptionCheck}, loop counter=$j", true, GLOBAL_LOG_ERROR)
			statusCode=(statusCode)?:3 //internal error            
		}                
		if ((statusCode) && (!exceptionCheck?.contains("Bad Request"))) {
			state?.retriesClimateCounter=(state?.retriesClimateCounter?:0)+1            
			if (!(interval=get_exception_interval_delay(state?.retriesClimateCounter))) {  
				traceEvent(settings.logFilter,"setClimate>error max retries, counter=${state?.retriesClimateCounter}, exiting", true, GLOBAL_LOG_ERROR)
				state?.retriesClimateCounter=0
				reset_replay_data('Climate')                
				return        
			}        
			data?."replayClimateId${state?.retriesClimateCounter}"=thermostatId
			data?."replayClimateName${state?.retriesClimateCounter}"=climateName
			data?."replayClimateParamsMap${state?.retriesClimateCounter}"=paramsMap            
			traceEvent(settings.logFilter,"setClimate>about to call setClimateReplay,retries counter=${state?.retriesClimateCounter}", true, GLOBAL_LOG_INFO)
			runIn(interval, "setClimateReplay",[overwrite:true])        
		}            

	} /* end for */    
    
}

void setClimateReplay() {
	for (int i=1; (i<= get_MAX_SETTER_RETRIES()); i++) {
		def id = data?."replayClimateId${i}"
		if (id==null) continue        
		def climateName=data?."replayClimateName${i}"
		def paramsMap=data?."replayClimateParamsMap${i}"    
		traceEvent(settings.logFilter,"setClimateReplay>about to recall setClimate for thermostatId=$id,retries counter=${state?.retriesClimateCounter}" +
			",climateName=$climateName, paramsMap=$paramsMap", true, GLOBAL_LOG_INFO)
		setClimate(id,climateName, paramsMap)
		exceptionCheck=device.currentValue("verboseTrace")
		if (exceptionCheck?.contains("done")) {
			data?."replayClimateId$i"=null        
		} /* end if */
	} /* end for */
	if (exceptionCheck?.contains("done")) { // if last command executed OK, then reset the counter
		reset_replay_data('Climate')                
		state?.retriesClimateCounter=0
	} /* end if */
}


// iterateUpdateClimate: iterate thru all the thermostats under a specific account and update their Climate
// tstatType =managementSet or registered (no spaces).  May also be set to a specific locationSet (ex./Toronto/Campus/BuildingA)
// climate name is the name of the climate to be updated (ex. "Home", "Away").
// deleteClimateFlag is set to 'true' if the climate needs to be deleted (should not be part of any schedule beforehand)
// subClimateName is the climateName that will replace the original climateName in the schedule (can be null when not needed)
// isOptimized is 'true' or 'false'
// isOccupied is 'true' or 'false'
// coolFan & heatFan's mode is 'auto' or 'on'
void iterateUpdateClimate(tstatType, climateName, deleteClimateFlag,
	subClimateName, coolTemp, heatTemp, isOptimized, isOccupied, coolFan, heatFan) {
    
	def ecobeeType = determine_ecobee_type_or_location(tstatType)
	getThermostatSummary(ecobeeType)
	traceEvent(settings.logFilter,"iterateUpdateClimate>ecobeeType=${ecobeeType},about to loop ${data.thermostatCount} thermostat(s)",settings.trace)
	for (i in 0..data.thermostatCount - 1) {
		def thermostatDetails = data.revisionList[i].split(':')
		def Id = thermostatDetails[0]
		def thermostatName = thermostatDetails[1]
		def connected = thermostatDetails[2]
		if (connected == 'true') {
			traceEvent(settings.logFilter,"iterateUpdateClimate> about to call updateClimate for thermostatId =${id}",settings.trace)
			updateClimate(Id, climateName, deleteClimateFlag, subClimateName, coolTemp,
				heatTemp, isOptimized, isOccupied, coolFan, heatFan)
		}
	}
}

// thermostatId may only be 1 thermostat (not a list) 
//	if no thermostatId is provided, it is defaulted to the current thermostatId 
// climate name is the name of the climate to be updated (ex. "Home", "Away").
// deleteClimateFlag is set to 'true' if the climate needs to be deleted (should not be part of any schedule beforehand)
// substituteClimateName is the climateName that will replace the original climateName in the schedule (can be null when not needed)
// isOptimized is 'true' or 'false'
// coolFan & heatFan's mode is 'auto' or 'on'
void updateClimate(thermostatId, climateName, deleteClimateFlag,
		substituteClimateName, coolTemp, heatTemp, isOptimized, isOccupied, coolFan, heatFan) {
	def ECOBEE_NEED_TOKEN_REFRESH=14
	Integer targetCoolTemp,targetHeatTemp
	def foundClimate = null, foundSubstituteClimate =null
	String scheduleAsString
	def substituteClimateRef = null
	def climateRefToBeReplaced = null

	if (thermostatId) {
//		call getThermostatInfo if a value for thermostatId is provided to make sure to have the right thermostat information

		getThermostatInfo(thermostatId) 
	}        
	thermostatId = determine_tstat_id(thermostatId)
	if ((coolTemp != null) && (heatTemp != null)) {
		traceEvent(settings.logFilter,"updateClimate>thermostatId =${thermostatId} coolTemp=${coolTemp}, heatTemp= ${heatTemp}",settings.trace)
		def scale = state?.scale
		if (scale == 'C') {
			targetCoolTemp = (cToF(coolTemp) * 10) as Integer // need to send temperature in F multiply by 10
			targetHeatTemp = (cToF(heatTemp) * 10) as Integer
		} else {
			targetCoolTemp = (coolTemp * 10) as Integer // need to send temperature in F multiply by 10
			targetHeatTemp = (heatTemp * 10) as Integer
		}
	}        
	if ((substituteClimateName != null) && (substituteClimateName != "")) { // find the subsituteClimateRef for the subsitute Climate Name if not null

		foundClimate = data.thermostatList[0].program.climates.find{ it.name.toUpperCase() == climateName.toUpperCase() }
		foundSubstituteClimate = data.thermostatList[0].program.climates.find { it.name.toUpperCase() == substituteClimateName.toUpperCase() }
		if (foundClimate) {
			climateRefToBeReplaced = foundClimate.climateRef       
			traceEvent(settings.logFilter,"updateClimate>climateRef ${climateRefToBeReplaced} found for climateName ${climateName}",settings.trace)
		} else {
			traceEvent(settings.logFilter,"updateClimate>climateName ${substituteClimateName} for substitution not found",settings.trace, GLOBAL_LOG_WARN)
			return
		}
		if (foundSubstituteClimate) {
 			substituteClimateRef = foundSubstituteClimate.climateRef       
			traceEvent(settings.logFilter,"updateClimate>substituteClimateRef ${substituteClimateRef} found for ${substituteClimateName} ",settings.trace)
		} else {
			traceEvent(settings.logFilter,"updateClimate>substituteClimateName ${substituteClimateName} for substitution not found",settings.trace, GLOBAL_LOG_WARN)
			return
		}
	}
	def bodyReq =
		'{"selection":{"selectionType":"thermostats","selectionMatch":"' +
		thermostatId + '"}'
	bodyReq = bodyReq + ',"thermostat":{"program":{"schedule":['
	for (i in 0..data.thermostatList[0].program.schedule.size() - 1) {
		bodyReq = bodyReq + '['
		// loop thru all the schedule items to create the json structure
		// replace the climateRef with right substituteClimateRef if needed
		for (j in 0..data.thermostatList[0].program.schedule[i].size() - 1) {
			if (substituteClimateRef != null) {
				scheduleAsString = (data.thermostatList[0].program.schedule[i][j] ==
					climateRefToBeReplaced) ?
					'"' + substituteClimateRef + '"' :
					'"' + data.thermostatList[0].program.schedule[i][j].toString() + '"'
			} else {
				scheduleAsString = '"' + data.thermostatList[0].program.schedule[i][j].toString() +
						'"'
			}
			bodyReq = (j == 0) ? bodyReq + scheduleAsString : bodyReq + ',' +
				scheduleAsString
		}
		bodyReq = (i == (data.thermostatList[0].program.schedule.size() - 1)) ?
			bodyReq + ']' : bodyReq + '],'
	}
	bodyReq = bodyReq + '],"climates":['
	foundClimate = false
	for (i in 0..data.thermostatList[0].program.climates.size() - 1) {
		if ((i != 0) && (i < data.thermostatList[0].program.climates.size())) {
			bodyReq = bodyReq + ','
		}
		if ((deleteClimateFlag == 'true') && (climateName.trim().toUpperCase() ==
			data.thermostatList[0].program.climates[i].name.toUpperCase())) {
			foundClimate = true
			traceEvent(settings.logFilter,"updateClimate>thermostatId =${thermostatId}, deleteClimateFlag=${deleteClimateFlag},Climate ${climateName} to be deleted...", settings.trace)
			bodyReq = bodyReq.substring(0, (bodyReq.length() - 1)) // trim the last ','
		} else if ((deleteClimateFlag == 'false') && (climateName.trim().toUpperCase() ==
			data.thermostatList[0].program.climates[i].name.toUpperCase())) {
			// update the Climate Object
			foundClimate = true
			bodyReq = bodyReq + '{"name":"' + data.thermostatList[0].program.climates[i]
				.name + '","climateRef":"' +
				data.thermostatList[0].program.climates[i].climateRef + '","coolTemp":"' +
				targetCoolTemp + '","heatTemp":"' + targetHeatTemp + '","isOptimized":"' + isOptimized +
					'","isOccupied":"' + isOccupied +                
					'","coolFan":"' + coolFan + '","heatFan":"' + heatFan + '"}'
		} else {
			bodyReq = bodyReq + '{"name":"' + data.thermostatList[0].program.climates[i]
				.name + '","climateRef":"' +
				data.thermostatList[0].program.climates[i].climateRef + '"}'
		}
	}
	if (!foundClimate) { // this is a new Climate object to create
		traceEvent(settings.logFilter,"updateClimate>thermostatId =${thermostatId},Climate ${climateName} to be created",settings.trace)

		bodyReq = bodyReq + ',{"name":"' + climateName.capitalize().trim() +
			'","coolTemp":"' + targetCoolTemp +
			'","heatTemp":"' + targetHeatTemp + '","isOptimized":"' + isOptimized +
			'","isOccupied":"' + isOccupied  + '","coolFan":"' + coolFan + '","heatFan":"' + heatFan + '"' +
			',"ventilatorMinOnTime":"5"}' // workaround due to ecobee create Climate bug, to be removed
	}
	bodyReq = bodyReq + ']}}}'

	String URI_ROOT = "${get_URI_ROOT()}/${get_API_VERSION()}"
	def args_encoded = java.net.URLEncoder.encode(bodyReq.toString(), "UTF-8")
    
	def request = [
		attributes: bodyReq
		]
        
	def params = [
		uri: "${URI_ROOT}/thermostat?format=json",
		headers: [
			'Authorization': "${data.auth.token_type} ${data.auth.access_token}",
			'Content-Type': "application/json",
			'charset': "UTF-8",
			'Accept': "application/json"
		],
		body: args_encoded
	]
	asynchttp_v1.post('updateClimateResponse', params, request)
	traceEvent(settings.logFilter,"updateClimate>Async command sent", settings.trace)	

}



def updateClimateResponse(response, data) {	
	def attributes = data["attributes"]

	def TOKEN_EXPIRED=401
	if (response.hasError()) {
		if (response?.status == TOKEN_EXPIRED) { // token is expired
			traceEvent(settings.logFilter,"updateClimateResponse>ecobee's Access token has expired for $attributes, trying to refresh tokens now...", settings.trace, GLOBAL_LOG_WARN)
			refresh_tokens()      
            
		} else {         
			traceEvent(settings.logFilter,"updateClimateResponse>ecobee response error: $response.errorMessage for $attributes", true, GLOBAL_LOG_ERROR)
			state?.exceptionCount=state?.exceptionCount +1        
		}        
	} else {
		if (response.status == 200) {
			traceEvent(settings.logFilter,"updateClimateResponse> - $attributes request sent successfully",settings.trace)
		} else {
			traceEvent(settings.logFilter,"updateClimateResponse> - $attributes request failed, error: $response.status",settings.trace,GLOBAL_LOG_ERROR)
			state?.exceptionCount=state?.exceptionCount +1        
		}
	}
}


// thermostatId may be 1 thermostat or a list of thermostats separated by ','. By default, it's the current thermostat
// See https://www.ecobee.com/home/developer/api/documentation/v1/objects/Audio.shtml  for more details on parameters
// playbackVolume Integer	
// microphoneEnabled Boolean (true or false)
// soundAlertVolume	Integer
// soundTickVolume Integer
// ex. device.updateAudio("",100)  // playbackVolume is 100 instead of 90
// ex. device.updateAudio("",100, false)  // playbackVolume is 100, microphone disabled.

void updateAudio(thermostatId, playbackVolume, microphoneEnabled=null, soundAlertVolume=null,soundTickVolume=null) {
	def ECOBEE_NEED_TOKEN_REFRESH=14
 	def audioEvents = [:]
	if (!thermostatId) {
		thermostatId = determine_tstat_id(thermostatId)
	}    
	traceEvent(settings.logFilter,"updateAudio>thermostatId=$thermostatId, playbackVolume=$playbackVolume, microphoneEnabled=$microphoneEnabled,soundAlertVolume=$soundAlertVolume,soundTickVolume=$soundTickVolume", settings.trace)	

	if ((playbackVolume != null) && ((!playbackVolume.isNumber()) || (playbackVolume < 0 || playbackVolume > 100))) {
		traceEvent(settings.logFilter,"updateAudio>playback volume is invalid ($playbackVolume), volume must be between 0 and 100", settings.trace, GLOBAL_LOG_ERROR)	
		return    
	}    
	if ((microphoneEnabled != null) && (!microphoneEnabled in ['true','false'])) {
		traceEvent(settings.logFilter,"updateAudio>microphone enabled must be a boolean value ($microphoneEnabled)", settings.trace, GLOBAL_LOG_ERROR)	
		return    
	}
	if ((soundAlertVolume != null) && ((!soundAlertVolume.isNumber()) || (soundAlertVolume < 0 || soundAlertVolume > 10))) {
		traceEvent(settings.logFilter,"updateAudio>soundAlertVolume  is invalid ($soundAlertVolume), soundAlertVolume must be between 0 and 10", settings.trace, GLOBAL_LOG_ERROR)	
		return    
	}    
	if ((soundTickVolume != null) && ((!soundTickVolume.isNumber()) || (soundTickVolume < 0 || soundTickVolume > 10))) {
		traceEvent(settings.logFilter,"updateAudio>soundTickVolume  is invalid ($soundTickVolume), soundAlertVolume must be between 0 and 10", settings.trace, GLOBAL_LOG_ERROR)	
		return    
	}    
    
	def bodyReq =
		'{"selection":{"selectionType":"thermostats","selectionMatch":"' +
		thermostatId + '"}'
	bodyReq = bodyReq + ',"thermostat":{"audio":{'
	if (playbackVolume != null) {
		bodyReq= bodyReq +  'playbackVolume:' +  playbackVolume + ',' 
		audioEvents=audioEvents + ['playbackVolume': playbackVolume.toString()]        
	}    
	if (microphoneEnabled != null) {
		bodyReq= bodyReq +  'microphoneEnabled:' +  microphoneEnabled + ','        
		audioEvents=audioEvents + ['microphoneEnabled': microphoneEnabled.toString()]        
	}    
	if (soundAlertVolume != null) {
		bodyReq= bodyReq +  'soundAlertVolume:' +  soundAlertVolume + ','        
		audioEvents=audioEvents + ['soundAlertVolume': soundAlertVolume.toString()]        
	}    
	if (soundTickVolume != null) {
		bodyReq= bodyReq +  'soundTickVolume:' +  soundTickVolume + ','        
		audioEvents=audioEvents + ['soundTickVolume':soundTickVolume.toString()]        
	}    
	bodyReq = bodyReq.substring(0, (bodyReq.length() - 1)) // trim the last ','
    
	bodyReq = bodyReq + '}}}'

	String URI_ROOT = "${get_URI_ROOT()}/${get_API_VERSION()}"
//	def args_encoded = java.net.URLEncoder.encode(bodyReq.toString(), "UTF-8")
    
	int statusCode=1
	int j=0        
	while ((statusCode) && (j++ <2)) { // retries once if api call fails
		def exceptionCheck
		api('updateAudio', bodyReq) {resp ->
			statusCode = resp?.data?.status?.code
			def message = resp?.data?.status?.message
			if ((resp?.status==TOKEN_EXPIRED) || ((resp?.status == 500) && (statusCode == ECOBEE_NEED_TOKEN_REFRESH))) {
				traceEvent(settings.logFilter,"updateAudio>thermostatId=${thermostatId},error $statusCode, need to call refresh_tokens()",settings.trace, GLOBAL_LOG_WARN)
				refresh_tokens()     
			}
			exceptionCheck=device.currentValue("verboseTrace")
			if (!statusCode) {
				/* when success, reset the exception counter */
				state.exceptionCount=0
				traceEvent(settings.logFilter,"updateAudio>done for thermostatId =${thermostatId}",settings.trace)
				// post Audio Events 
				generateEvent(audioEvents)
			} else {
				state.exceptionCount = state.exceptionCount +1     
				traceEvent(settings.logFilter,"updateAudio>error ${statusCode.toString()},message=${message} for thermostatId =${thermostatId},plugName =${plugName}", true, GLOBAL_LOG_ERROR)
			} /* end if statusCode */
		} /* end api call */               
		if (exceptionCheck?.contains("exception")) {
			sendEvent(name: "verboseTrace", value: "", displayed:(settings.trace?:false)) // reset verboseTrace            
			traceEvent(settings.logFilter,"updateAudio>exception=${exceptionCheck}, loop counter=$j", true, GLOBAL_LOG_ERROR)
			statusCode=(statusCode)?:3 //internal error            
		}                
	} /* end while */

}

// controlPlug() only works with Smart-02 thermostats as smart plugs are now obsolete
// thermostatId may only be 1 thermostat (not a list) 
//	if no thermostatId is provided, it is defaulted to the current thermostatId 
// plugName is the name of the plug name to be controlled 
// plugState is the state to be set
// plugSettings are the different settings at https://www.ecobee.com/home/developer/api/documentation/v1/functions/ControlPlug.shtml
void controlPlug(thermostatId, plugName, plugState, plugSettings = []) {
	def ECOBEE_NEED_TOKEN_REFRESH=14
	def TOKEN_EXPIRED=401    
	def plugSettingsJson = new groovy.json.JsonBuilder(plugSettings)
	def plugSet = plugSettingsJson.toString().minus('{').minus('}')

	thermostatId = determine_tstat_id(thermostatId)
	traceEvent(settings.logFilter,"updateGroup> about to assemble bodyReq for plugName =${plugName}, thermostatId = ${thermostatId}, plugSettings=${plugSet}",settings.trace)
	def bodyReq =
		'{"selection":{"selectionType":"thermostats","selectionMatch":"' +
		thermostatId + '"}'
	bodyReq = bodyReq +
		',"functions":[{"type":"controlPlug","params":{"plugName":"' + plugName +
		'","plugState":"' + plugState + '"'

	// add the plugSettings if any
	if (plugSettings) {
		bodyReq = bodyReq + ',' + plugSet
	}
	bodyReq = bodyReq + '}}]}'
	int statusCode=1
	int j=0        
	while ((statusCode) && (j++ <2)) { // retries once if api call fails
		def exceptionCheck
		api('controlPlug', bodyReq) {resp ->
			statusCode = resp?.data?.status?.code
			def message = resp?.data?.status?.message
			if ((resp?.status==TOKEN_EXPIRED) || ((resp?.status == 500) && (statusCode == ECOBEE_NEED_TOKEN_REFRESH))) {
				traceEvent(settings.logFilter,"controlPlug>thermostatId=${thermostatId},error $statusCode, need to call refresh_tokens()",settings.trace, GLOBAL_LOG_WARN)
				refresh_tokens()     
			}
			exceptionCheck=device.currentValue("verboseTrace")
			if (!statusCode) {
				/* when success, reset the exception counter */
				state.exceptionCount=0
				traceEvent(settings.logFilter,"controlPlug>done for thermostatId =${thermostatId},plugName =${plugName}",settings.trace)
				// post plug values 
 				def plugEvents = [
					plugName: plugName, 
 					plugState: plugState
 				]
                
				if (plugSettings) {
					plugEvents = plugEvents + [plugSettings: plugSet]
				}
				generateEvent(plugEvents)
			} else {
				state.exceptionCount = state.exceptionCount +1     
				traceEvent(settings.logFilter,"controlPlug>error ${statusCode.toString()},message=${message} for thermostatId =${thermostatId},plugName =${plugName}", true, GLOBAL_LOG_ERROR)
			} /* end if statusCode */
		} /* end api call */               
		if (exceptionCheck?.contains("exception")) {
			sendEvent(name: "verboseTrace", value: "", displayed:(settings.trace?:false)) // reset verboseTrace            
			traceEvent(settings.logFilter,"controlPlug>exception=${exceptionCheck}, loop counter=$j", true, GLOBAL_LOG_ERROR)
			statusCode=(statusCode)?:3 //internal error            
		}                
	} /* end while */
}
// thermostatId shall refer to a single thermostat to avoid processing too much data
//	if no thermostatId is provided, it is defaulted to the current thermostatId 
// reportColumn shall be 1 component only (ex. auxHeat1, coolComp1, fan, ventilator, humidifier, dehumidifier)
// startDateTime and endDateTime should be in UTC timezone format
// startInterval & endInterval may be null. In that case, the intervals are calculated using the startDateTime and endDateTime
// includeSensorData may be 'true' or 'false'
// postData may be 'true' or 'false', by default the latter

// See https://www.ecobee.com/home/developer/api/documentation/v1/operations/get-runtime-report.shtml for more details
// If you want to post the data, then set postData to 'true', by default, reportdata and sensorData are not posted
//
// 	One can loop on the reportData or sensorData with the following logic 
//		ecobee.getReportData("", oneHourAgo, now, null, null, "dehumidifier",'false','true')
//		def reportData = ecobee.currentReportData.toString().split(",,")
//            
//		for (i in 0..reportData.size()-1) {
//			def rowDetails
//			try {
//				rowDetails = reportData[i].split(',')
//				def dateReportData = rowDetails[0]
//				def timeReportData = rowDetails[1]
//				def valueReportData = rowDetails[2]
//			
//			} catch (any)
//				log.error "no values ($rowDetails) for $component at $i"  // values are not always provided
//				continue
//			} 
//		}

void getReportData(thermostatId, startDateTime, endDateTime, startInterval, endInterval, reportColumn, includeSensorData, postData='false') {
	def ECOBEE_NEED_TOKEN_REFRESH=14
	def TOKEN_EXPIRED=401    
	double TOTAL_MILLISECONDS_PER_DAY=(24*60*60*1000)	
	def REPORT_TIME_INTERVAL=5
	def REPORT_MAX_INTERVALS_PER_DAY=287
	int beginInt, endInt

	thermostatId = determine_tstat_id(thermostatId)
	double nbDaysInPeriod = ((endDateTime.getTime() - startDateTime.getTime()) /TOTAL_MILLISECONDS_PER_DAY).round(2)
        
	if (nbDaysInPeriod > 31) {  // Report period should not be bigger than 31 days to avoid summarizing too much data.
		traceEvent(settings.logFilter,"getReportData> report's period too big (${nbDaysInPeriod.toString()} > 2)", true, GLOBAL_LOG_ERROR)
		return
	} 
	if (thermostatId.contains(',')) {  // Report should run on a single thermostat only
		traceEvent(settings.logFilter,"getReportData> report should run on a single thermostatId only (${thermostatId})", true, GLOBAL_LOG_ERROR)
		return
	}
    
	if (reportColumn.contains(',')) {  // Report should run on a single component only
		traceEvent(settings.logFilter,"getReportData> report should run on a single component only (${reportColumn})",true, GLOBAL_LOG_ERROR)
		return
	}
	traceEvent(settings.logFilter,"getReportData> startDate in UTC timezone =${String.format('%tF %<tT',startDateTime)}," +
        	"endDate in UTC timezone =${String.format('%tF %<tT',endDateTime)}", settings.trace)
   	state?.reportRevision = newReportRevision
	state?.componentReport = reportColumn
    
	beginInt = (startInterval == null)? get_interval(startDateTime): startInterval.toInteger()
	endInt = (endInterval == null)? get_interval(endDateTime): endInterval.toInteger()
	Calendar startCalendar = startDateTime.toCalendar()
	Calendar endCalendar = endDateTime.toCalendar()
	traceEvent(settings.logFilter,"getReportData> startInterval = ${beginInt}, endInterval = ${endInt}",settings.trace)
	def bodyReq = '{"startInterval":"' + beginInt.toString() + '","endInterval":"' + endInt.toString() + 
    				'","startDate":"' + String.format('%tY-%<tm-%<td',startDateTime) + '",' + '"endDate":"' +
					String.format('%tY-%<tm-%<td',endDateTime) + '",' +
					'"columns":"' +  reportColumn + '","includeSensors":"' + includeSensorData + '",' +
					'"selection":{"selectionType":"thermostats","selectionMatch":"' + thermostatId + '"}}'
	traceEvent(settings.logFilter,"getReportData> about to call api with body = ${bodyReq} for thermostatId = ${thermostatId}...",settings.trace)
	int statusCode=1
	int j=0        
	while ((statusCode) && (j++ <2)) { // retries once if api call fails
		def exceptionCheck    
		api('runtimeReport', bodyReq) {resp ->
			statusCode = resp?.data?.status?.code
			def message = resp?.data?.status?.message
			if ((resp?.status==TOKEN_EXPIRED) || ((resp?.status == 500) && (statusCode == ECOBEE_NEED_TOKEN_REFRESH))) {
				traceEvent(settings.logFilter,"getReportData>thermostatId=${thermostatId},error $statusCode, need to call refresh_tokens()",settings.trace, GLOBAL_LOG_WARN)
				refresh_tokens()     
			}
			exceptionCheck=device.currentValue("verboseTrace")
			if (!statusCode) {
				/* when success, reset the exception counter */
				state.exceptionCount=0
				data.reportList = resp.data.reportList
				data.startDate = resp.data.startDate
				data.endDate = resp.data.endDate
				data.startInterval = resp.data.startInterval
				data.endInterval = resp.data.endInterval
				data.columns = resp.data.columns
				if (includeSensorData=='true') {
					data.sensorList =  resp.data.sensorList
				}  
				def reportData = ""
				def reportSensorMetadata=""
				def reportSensorData =""
                
				if (postData=='true') {
					traceEvent(settings.logFilter,"getReportData>about to post reportData = $data.reportList.rowList[0].toString()",settings.trace)
					reportData = data.reportList?.rowList[0].toString().minus('[').minus(']')
					if (includeSensorData=='true') {
						reportSensorMetadata = new groovy.json.JsonBuilder(data.sensorList?.sensors[0])  // metadata is in Json format
						reportSensorData = data.sensorList?.data[0].toString().minus('[').minus(']')
					}   
				}   
				generateEvent(['reportData':reportData,'reportSensorMetadata':reportSensorMetadata,
					'reportSensorData':reportSensorData])
				traceEvent(settings.logFilter,"getReportData> startDate= ${data.startDate}",settings.trace)
				traceEvent(settings.logFilter,"getReportData> endDate= ${data.endDate}",settings.trace)
				traceEvent(settings.logFilter,"getReportData> startInterval= ${data.startInterval}",settings.trace)
				traceEvent(settings.logFilter,"getReportData> endInterval= ${data.endInterval}",settings.trace)
				traceEvent(settings.logFilter,"getReportData> columns= ${data.columns}",settings.trace)
				traceEvent(settings.logFilter,"getReportData> reportList= ${data.reportList}",settings.trace)
				traceEvent(settings.logFilter,"getReportData> sensorList= ${data.sensorList}",settings.trace)
				traceEvent(settings.logFilter,"getReportData> postData= ${postData}",settings.trace)
				traceEvent(settings.logFilter,"getReportData>done for thermostatId ${thermostatId}", settings.trace)
        	        
			} else {
				state.exceptionCount = state.exceptionCount +1     
				traceEvent(settings.logFilter,"getReportData>error=${statusCode},message= ${message} for ${thermostatId}",settings.trace)			
			} /* end if statusCode */
		} /* end api call */                
		if (exceptionCheck?.contains("exception")) {
			sendEvent(name: "verboseTrace", value: "", displayed:(settings.trace?:false)) // reset verboseTrace            
			traceEvent(settings.logFilter,"getReportData>exception=${exceptionCheck}, loop counter=$j",true, GLOBAL_LOG_ERROR)
			statusCode=(statusCode)?:3 //internal error            
		}                
	} /* end while */
}

private int get_interval(Date dateTime) {
	def REPORT_TIME_INTERVAL=5
	Calendar c = dateTime.toCalendar()
	int intervalHr = (c.get(Calendar.HOUR_OF_DAY)>0) ? 
		(c.get(Calendar.HOUR_OF_DAY) * 60) / REPORT_TIME_INTERVAL :0 
	int intervalMin = (c.get(Calendar.MINUTE)>= REPORT_TIME_INTERVAL) ? 
		c.get(Calendar.MINUTE) / REPORT_TIME_INTERVAL :0 
	traceEvent(settings.logFilter,"get_interval> Calendar hour= ${c.get(Calendar.HOUR_OF_DAY)}",settings.trace)
	traceEvent(settings.logFilter,"get_interval> Calendar minute= ${c.get(Calendar.MINUTE)}",settings.trace)
	traceEvent(settings.logFilter,"get_interval> intervalHr ${intervalHr}",settings.trace)
	traceEvent(settings.logFilter,"get_interval> intervalMin= ${intervalMin}",settings.trace)
	return (intervalHr + intervalMin)
}

// getReportData() must be called prior to calling generateReportRuntimeEvents() function
// component may be auxHeat1, compCool1, fan, ventilator, humidifier, dehumidifier, etc.
// startInterval & endInterval may be null. 
//	Intervals will be then defaulted to the ones used to generate the report
// typeEvent may be 'daily' or others

void generateReportRuntimeEvents(component, startDateTime, endDateTime, startInterval, endInterval, typeEvent ='daily') {
	double TOTAL_MILLISECONDS_PER_DAY=(24*60*60*1000)	
	def REPORT_TIME_INTERVAL=5
	def REPORT_MAX_INTERVALS_PER_DAY=287
	int beginInt, endInt
    
	double nbDaysInPeriod = ((endDateTime.getTime() - startDateTime.getTime()) /TOTAL_MILLISECONDS_PER_DAY).round(2)
        
	if (nbDaysInPeriod > 31) {  // Report period should not be bigger than 31 days to avoid summarizing too much data.
		traceEvent(settings.logFilter,"generateReportRuntimeEvents> report's period too big (${nbDaysInPeriod.toString()} > 2)", true, GLOBAL_LOG_ERROR)
		return
	} 

	double totalRuntime, avgRuntime
	double runtimeInMin   // Calculations are done in minutes
    
	beginInt = (startInterval == null)? get_interval(startDateTime): startInterval.toInteger()
	endInt = (endInterval == null)? get_interval(endDateTime): endInterval.toInteger()
	Calendar startCalendar = startDateTime.toCalendar()
	Calendar endCalendar = endDateTime.toCalendar()
	if ((endCalendar.get(Calendar.MONTH) != startCalendar.get(Calendar.MONTH)) || 
		(endCalendar.get(Calendar.DATE) != startCalendar.get(Calendar.DATE))) {
		endInt += nbDaysInPeriod.intValue() * REPORT_MAX_INTERVALS_PER_DAY 
	}
	traceEvent(settings.logFilter,"generateReportRuntimeEvents> startInterval = ${beginInt}, endInterval = ${endInt}",settings.trace)
	if (component.contains('auxHeat1')) {
		totalRuntime = calculate_report_stats('auxHeat1', beginInt, endInt, 'report')
		if (typeEvent== 'daily') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 60).round(2) :0
 			sendEvent name: "auxHeat1RuntimeDaily", value: "${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'yesterday') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 60).round(2) :0
			sendEvent name: "auxHeat1RuntimeYesterday", value: "${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'weekly') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 7 / 60).round(2) :0
			sendEvent name: "auxHeat1RuntimeAvgWeekly", value: "${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'monthly') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 30 / 60).round(2) :0
			sendEvent name: "auxHeat1RuntimeAvgMonthly", value: "${runtimeInMin.toString()}",isStateChange:true
		} else {	            
 			sendEvent name: "auxHeat1RuntimeInPeriod", value: "${runtimeInMin.toString()}",isStateChange:true
		}
	}
	if (component.contains('auxHeat2')) {
		totalRuntime = calculate_report_stats('auxHeat2', beginInt, endInt,'report')
		if (typeEvent== 'daily') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 60).round(2) :0
			sendEvent name: "auxHeat2RuntimeDaily", value: "${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'yesterday') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 60).round(2) :0
			sendEvent name: "auxHeat2RuntimeYesterday", value:"${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'weekly') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 7 / 60).round(2) :0
			sendEvent name: "auxHeat2RuntimeAvgWeekly", value: "${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'monthly') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 30 / 60).round(2) :0
			sendEvent name: "auxHeat2RuntimeAvgMonthly", value: "${runtimeInMin.toString()}",isStateChange:true
 		} else {           
			sendEvent name: "auxHeat2RuntimeInPeriod", value: "${runtimeInMin.toString()}",isStateChange:true
		}
	}
	if (component.contains('auxHeat3')) {
		totalRuntime = calculate_report_stats('auxHeat3', beginInt, endInt,'report')
		if (typeEvent== 'daily') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 60).round(2) :0
			sendEvent name: "auxHeat3RuntimeDaily", value: "${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'yesterday') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 60).round(2) :0
			sendEvent name: "auxHeat3RuntimeYesterday", value: "${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'weekly') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 7 / 60).round(2) :0
			sendEvent name: "auxHeat3RuntimeAvgWeekly", value: "${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'monthly') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 30 / 60).round(2) :0
			sendEvent name: "auxHeat3RuntimeAvgMonthly", value: "${runtimeInMin.toString()}",isStateChange:true
		} else {            
			sendEvent name: "auxHeat3RuntimeInPeriod", value: "${runtimeInMin.toString()}",isStateChange:true
		}
	}
	if (component.contains('compHeat1')) {
		totalRuntime = calculate_report_stats('compHeat1', beginInt, endInt, 'report')
		if (typeEvent== 'daily') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 60).round(2) :0
 			sendEvent name: "compHeat1RuntimeDaily", value: "${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'yesterday') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 60).round(2) :0
			sendEvent name: "compHeat1RuntimeYesterday", value: "${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'weekly') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 7 / 60).round(2) :0
			sendEvent name: "compHeat1RuntimeAvgWeekly", value: "${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'monthly') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 30 / 60).round(2) :0
			sendEvent name: "compHeat1RuntimeAvgMonthly", value: "${runtimeInMin.toString()}",isStateChange:true
		} else {	            
 			sendEvent name: "compHeat1RuntimeInPeriod", value: "${runtimeInMin.toString()}",isStateChange:true
		}
	}
	if (component.contains('compHeat2')) {
		totalRuntime = calculate_report_stats('compHeat2', beginInt, endInt,'report')
		if (typeEvent== 'daily') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 60).round(2) :0
			sendEvent name: "compHeat2RuntimeDaily", value: "${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'yesterday') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 60).round(2) :0
			sendEvent name: "compHeat2RuntimeYesterday", value:"${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'weekly') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 7 / 60).round(2) :0
			sendEvent name: "compHeat2RuntimeAvgWeekly", value: "${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'monthly') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 30 / 60).round(2) :0
			sendEvent name: "compHeat2RuntimeAvgMonthly", value: "${runtimeInMin.toString()}",isStateChange:true
 		} else {           
			sendEvent name: "compHeat2RuntimeInPeriod", value: "${runtimeInMin.toString()}",isStateChange:true
		}
	}
	if (component.contains('compHeat3')) {
		totalRuntime = calculate_report_stats('compHeat3', beginInt, endInt,'report')
		if (typeEvent== 'daily') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 60).round(2) :0
			sendEvent name: "compHeat3RuntimeDaily", value: "${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'yesterday') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 60).round(2) :0
			sendEvent name: "compHeat3RuntimeYesterday", value: "${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'weekly') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 7 / 60).round(2) :0
			sendEvent name: "compHeat3RuntimeAvgWeekly", value: "${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'monthly') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 30 / 60).round(2) :0
			sendEvent name: "compHeat3RuntimeAvgMonthly", value: "${runtimeInMin.toString()}",isStateChange:true
		} else {            
			sendEvent name: "compHeat3RuntimeInPeriod", value: "${runtimeInMin.toString()}",isStateChange:true
		}
	}
	if (component.contains('compCool1')) {
		totalRuntime = calculate_report_stats('compCool1', beginInt, endInt,'report')
		if (typeEvent== 'daily') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 60).round(2) :0
			sendEvent name: "compCool1RuntimeDaily", value: "${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'yesterday') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 60).round(2) :0
			sendEvent name: "compCool1RuntimeYesterday", value: "${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'weekly') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 7 / 60).round(2) :0
			sendEvent name: "compCool1RuntimeAvgWeekly", value: "${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'monthly') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 30 / 60).round(2) :0
			sendEvent name: "compCool1RuntimeAvgMonthly", value: "${runtimeInMin.toString()}",isStateChange:true
		} else {            
			sendEvent name: "compCool1RuntimeInPeriod", value: "${runtimeInMin.toString()}",isStateChange:true
		}
	}
	if (component.contains('compCool2')) {
		totalRuntime = calculate_report_stats('coolComp2', beginInt, endInt,'report')
		if (typeEvent== 'daily') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 60).round(2) :0
			sendEvent name: "coolComp2RuntimeDaily", value: "${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'yesterday') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 60).round(2) :0
			sendEvent name: "compCool2RuntimeYesterday", value:"${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'weekly') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 7 / 60).round(2) :0
			sendEvent name: "compCool2RuntimeAvgWeekly", value: "${runtimeInMin.toString()}",isStateChange:true
		} else if (typeEvent== 'monthly') {
			runtimeInMin = (totalRuntime >60) ? (totalRuntime / 30 / 60).round(2) :0
			sendEvent name: "compCool2RuntimeAvgMonthly", value: "${runtimeInMin.toString()}",isStateChange:true
		} else {            
			sendEvent name: "coolComp2RuntimeInPeriod", value: "${runtimeInMin.toString()}",isStateChange:true
		}
	}
	if (component.contains('fan')) {
		totalRuntime = calculate_report_stats('fan', beginInt, endInt,'report')
		runtimeInMin = (totalRuntime >60) ? (totalRuntime / 60).round(2) :0
		if (typeEvent== 'daily') {
			sendEvent name: "fanRuntimeDaily", value: "${runtimeInMin.toString()}",isStateChange:true
		} else {            
			sendEvent name: "fanRuntimeInPeriod", value: "${runtimeInMin.toString()}",isStateChange:true
		}
	}
	if (component.contains('ventilator')) {
 		totalRuntime = calculate_report_stats('ventilator', beginInt, endInt,'report')
		runtimeInMin = (totalRuntime >60) ? (totalRuntime / 60).round(2) :0
		if (typeEvent== 'daily') {
			sendEvent name: "ventilatorRuntimeDaily", value: "${runtimeInMin.toString()}",isStateChange:true
		} else {            
			sendEvent name: "ventilatorRuntimeInPeriod", value: "${runtimeInMin.toString()}",isStateChange:true
		}
	}
	if (component.contains('dehumidifier')) {
		totalRuntime = calculate_report_stats('dehumidifier', beginInt, endInt,'report')
		runtimeInMin = (totalRuntime >60) ? (totalRuntime / 60).round(2) :0
		if (typeEvent== 'daily') {
			sendEvent name: "dehumidifierRuntimeDaily", value: "${runtimeInMin.toString()}",isStateChange:true
		} else {            
			sendEvent name: "dehumidifierRuntimeInPeriod", value: "${runtimeInMin.toString()}",isStateChange:true
		}                
	} else if (component.contains('humidifier')) {
		totalRuntime = calculate_report_stats('humidifier', beginInt, endInt,'report')
		runtimeInMin = (totalRuntime >60) ? (totalRuntime / 60).round(2) :0
		if (typeEvent== 'daily') {
			sendEvent name: "humidifierRuntimeDaily", value: "${runtimeInMin.toString()}",isStateChange:true
		} else {            
			sendEvent name: "humidifierRuntimeInPeriod", value: "${runtimeInMin.toString()}",isStateChange:true
                
		}
	}
}
// getReportData() must be called prior to calling the generateReportSensorStatsEvents
// sensorId may be null or a specific sensorId as specified in the sensor metadata
//	see https://www.ecobee.com/home/developer/api/documentation/v1/objects/RuntimeSensorMetadata.shtml
// startInterval & endInterval may be null. 
//	Intervals will be then defaulted to the ones used to generate the report
// operation may be one or several stats separated by comma (ex. avg, min, max, total)

void generateReportSensorStatsEvents(sensorId, startDateTime, endDateTime, startInterval, endInterval, operation) {
	double TOTAL_MILLISECONDS_PER_DAY=(24*60*60*1000)	
	def REPORT_TIME_INTERVAL=5
	def REPORT_MAX_INTERVALS_PER_DAY=287
	int beginInt, endInt
	boolean foundSensor=false
    
	double nbDaysInPeriod = ((endDateTime.getTime() - startDateTime.getTime()) /TOTAL_MILLISECONDS_PER_DAY).round(2)

	float runtimeSensorStat
    
	beginInt = (startInterval == null)? get_interval(startDateTime): startInterval.toInteger()
	endInt = (endInterval == null)? get_interval(endDateTime): endInterval.toInteger()
	Calendar startCalendar = startDateTime.toCalendar()
	Calendar endCalendar = endDateTime.toCalendar()
	if (endCalendar.get(Calendar.DATE) != startCalendar.get(Calendar.DATE)) {
		endInt += nbDaysInPeriod.intValue() * REPORT_MAX_INTERVALS_PER_DAY 
	}
	traceEvent(settings.logFilter,"generateSensorRuntimeEvents> startInterval = ${beginInt}, endInterval = ${endInt}",settings.trace)
	if (sensorId != null) {
		foundSensor = data.sensorList.sensors[0].find{ it.sensorId == sensorId }
	}
	if (!foundSensor) {
		traceEvent(settings.logFilter,"generateReportSensorStatsEvents> sensor ${sensorId} not found in last sensor data from getReportData()",true, GLOBAL_LOG_ERROR)
		return
	}    
	if (operation.contains('avg')) {
		runtimeSensorStat = calculate_report_stats(sensorId, beginInt, endInt, 'sensor', 'avg')
 		sendEvent name: "reportSensorAvgInPeriod", value: runtimeSensorStat.round(2).toString() 
	}
	if (operation.contains('min')) {
		runtimeSensorStat = calculate_report_stats(sensorId, beginInt, endInt, 'sensor', 'min')
 		sendEvent name: "reportSensorMinInPeriod", value: runtimeSensorStat.round(2).toString()
	}
	if (operation.contains('max')) {
		runtimeSensorStat = calculate_report_stats(sensorId, beginInt, endInt, 'sensor', 'max')
 		sendEvent name: "reportSensorMaxInPeriod", value: runtimeSensorStat.round(2).toString()
	}
	if (operation.contains('total')) {
		runtimeSensorStat = calculate_report_stats(sensorId, beginInt, endInt, 'sensor')
 		sendEvent name: "reportSensorTotalInPeriod", value: runtimeSensorStat.round(2).toString()
	}
}

private def calculate_report_stats(component, startInterval, endInterval, typeData, operation='total') {
	double total=0	
	int nbRows=0
	int max=0
	def min = null
	int rowValue
    
	int startRow = (startInterval != null) ? startInterval: data.startInterval.toInteger()
	int rowCount = (typeData=='sensor')? data.sensorList.data[0].size(): data.reportList.rowList[0].size()
	int lastRow =  Math.min(endInterval,rowCount)

	traceEvent(settings.logFilter,"calculate_report_stats> about to process rowCount= ${rowCount},startRow=${startRow},lastRow=${lastRow}",settings.trace)
	if (lastRow <= startRow) {    
		traceEvent(settings.logFilter,"calculate_report_stats>lastRow=${lastRow} is not greater than startRow=${startRow}",true, GLOBAL_LOG_ERROR)
		return null
	}
	for (i in startRow..lastRow -1) {
		def rowDetails = (typeData=='sensor')? data.sensorList.data[0][i].split(","): data.reportList.rowList[0][i].split(",")
		try {
			rowValue = rowDetails[2]?.toInteger()
			total += rowValue
			nbRows++
			max = Math.max(rowValue,max)
			min = (min==null) ? rowValue : Math.min(rowValue,min)
		} catch (any) {
			traceEvent(settings.logFilter,"calculate_report_stats> no values ($rowDetails) for $component at $i",settings.trace, GLOBAL_LOG_WARN)
			continue
		}	
	}
    
	int avg = ((nbRows >1) ? total/nbRows:total)
	traceEvent(settings.logFilter,"calculate_report_stats> total= ${total} for $component component",settings.trace)
	traceEvent(settings.logFilter,"calculate_report_stats> nbRows with value= ${nbRows} for $component component",settings.trace)
	traceEvent(settings.logFilter,"calculate_report_stats> avg= ${avg} for $component component",settings.trace)
	traceEvent(settings.logFilter,"calculate_report_stats> max= ${max} for $component component",settings.trace)
	traceEvent(settings.logFilter,"calculate_report_stats> min= ${min} for $component component",settings.trace)
	if (operation == 'avg') {
		return avg    
	}
	if (operation == 'max') {	
		return max    	
	}
	if (operation == 'min') {	
		return min    	
	}
	return total
}

// thermostatId may be a list of serial# separated by ",", no spaces (ex. '123456789012,123456789013') 
//	if no thermostatId is provided, it is defaulted to the current thermostatId 
void getRemoteSensorUpdate(thermostatId=settings.thermostatId) {
	def ECOBEE_NEED_TOKEN_REFRESH=14
	def TOKEN_EXPIRED=401    
	def interval
    
	def bodyReq = build_body_request('remoteSensorUpdate',null,thermostatId,null)
	traceEvent(settings.logFilter,"getRemoteSensorUpdate> about to call api with body = ${bodyReq} for thermostatId = ${thermostatId}...",settings.trace)
	int statusCode=1
	def exceptionCheck    
	api('thermostatInfo', bodyReq) {resp ->
		statusCode = resp?.data?.status?.code
		def message = resp?.data?.status?.message
		if ((resp?.status==TOKEN_EXPIRED) || ((resp?.status == 500) && (statusCode == ECOBEE_NEED_TOKEN_REFRESH))) {
			traceEvent(settings.logFilter,"getRemoteSensorUpdate>thermostatId=${thermostatId},error $statusCode, need to call refresh_tokens()", settings.trace, GLOBAL_LOG_WARN)
			refresh_tokens()     
		}
		exceptionCheck=device.currentValue("verboseTrace")
		if (!statusCode) {
			/* when success, reset the exception counter */
			state.exceptionCount=0
			state?.retriesCounter=0            
			data?.thermostatList = resp.data.thermostatList
			def thermostatName = data.thermostatList[0].name
			if (data.thermostatList[0]?.remoteSensors) {
				traceEvent(settings.logFilter,"getRemoteSensorUpdate>found remote sensor values for thermostatId=${thermostatId},name=${thermostatName}",settings.trace)
			} else {
				traceEvent(settings.logFilter,"getRemoteSensorUpdate>No remote sensor values for thermostatId=${thermostatId},name=${thermostatName}",settings.trace)
			}        
			traceEvent(settings.logFilter,"getRemoteSensorUpdate>done for ${thermostatId}",settings.trace)
		} else {
			state.exceptionCount = state.exceptionCount +1     
			traceEvent(settings.logFilter,"getRemoteSensorUpdate>error=${statusCode},message=${message} for ${thermostatId}",true, GLOBAL_LOG_ERROR)
		} /* end if statusCode */                 
	} /* end api call */
	if (exceptionCheck?.contains("exception")) {
		sendEvent(name: "verboseTrace", value: "", displayed:(settings.trace?:false)) // reset verboseTrace            
		traceEvent(settings.logFilter,"getRemoteSensorUpdate>exception=${exceptionCheck}, loop counter=$j",true, GLOBAL_LOG_ERROR)
		statusCode=(statusCode)?:3 //internal error            
	}  
    
	if ((statusCode) || (exceptionCheck?.contains("ConnectTimeoutException"))) {
		state?.retriesCounter=(state?.retriesCounter?:0)+1            
		if (!(interval=get_exception_interval_delay( state?.retriesCounter, "GETTER"))) {  
			traceEvent(settings.logFilter,"getRemoteSensorUpdate>error max retries, counter=${state.retriesCounter}, exiting", true, GLOBAL_LOG_ERROR)
			return        
		}        
		data?.replayRemoteSensorId=thermostatId
		traceEvent(settings.logFilter,"getRemoteSensorUpdate>about to call getRemoteSensorsUpdateReplay,retries counter=${state.retriesCounter}, interval=$interval", true, GLOBAL_LOG_INFO)
		runIn(interval, "getRemoteSensorUpdateReplay",[overwrite:true])        
	}    
    
}

void getRemoteSensorsUpdateReplay() {
	def id = data?.replayRemoteSensorId
	traceEvent(settings.logFilter,"getRemoteSensorUpdateReplay>about to recall getRemoteSensorsUpdate for thermostatId=$id,retries counter=${state.retriesCounter}",
		true, GLOBAL_LOG_INFO)
	getRemoteSensorUpdate(id)
}
// getThermostatInfo() or getRemoteSensorUpdate() must be called before calling generateRemoteSensorEvents
// thermostatId shall refer to a single thermostat to avoid processing too much data
//	if no thermostatId is provided, it is defaulted to the current thermostatId 
// postData may be true or false, by default the latter
// bypassThrottling may be true or false, by default the latter

void generateRemoteSensorEvents(thermostatId,postData=false,bypassThrottling=false) {
	def REMOTE_SENSOR_TYPE="ecobee3_remote_sensor"
	def REMOTE_THERMOSTAT_TYPE="thermostat"
	def REMOTE_SENSOR_OCCUPANCY='occupancy'
	def REMOTE_SENSOR_TEMPERATURE='temperature'
	def REMOTE_SENSOR_HUMIDITY='humidity'
	def interval
    
	int nbTempSensorInUse=0
	int nbHumSensorInUse=0
	float value,totalTemp=0,totalHum=0, avgTemp=0, avgHum=0
	float maxTemp=0, maxHum=0
	def minTemp=null
	def minHum=null
    
	if ((thermostatId!=null) && (thermostatId !="")) {
		if (thermostatId.contains(",")) {
			traceEvent(settings.logFilter,"generateRemoteSensorEvents>thermostatId ${thermostatId} is not valid", true, GLOBAL_LOG_ERROR)
			return
		}
	} else {	
		thermostatId = determine_tstat_id(thermostatId)
	}
	if (bypassThrottling) {      
		getRemoteSensorUpdate(thermostatId)    
	}
	    
     	
	/* Reset all remote sensor data values */
	def remoteData = []
	def remoteTempData = ""
	def remoteHumData = ""
	def remoteOccData = ""
    
	traceEvent(settings.logFilter,"generateRemoteSensorEvents>found sensors ${data.thermostatList[0]?.remoteSensors}", settings.trace)
	if (data.thermostatList[0]?.remoteSensors) {
		for (i in 0..data.thermostatList[0].remoteSensors.size() - 1) {
			traceEvent(settings.logFilter,"generateRemoteSensorEvents>found sensor ${data.thermostatList[0].remoteSensors[i]} at (${i})", settings.trace)
			if ((data.thermostatList[0].remoteSensors[i]?.type != REMOTE_SENSOR_TYPE) &&
			 (data.thermostatList[0].remoteSensors[i]?.type != REMOTE_THERMOSTAT_TYPE)) {
				traceEvent(settings.logFilter,"generateRemoteSensorEvents>found sensor type ${data.thermostatList[0].remoteSensors[i].type} at (${i}, skipping it)",settings.trace, GLOBAL_LOG_WARN)
 				// not a remote sensor
 				continue
			}
			if (!data.thermostatList[0].remoteSensors[i].capability) {
				traceEvent(settings.logFilter,"generateRemoteSensorEvents>looping i=${i}, no capability values found...",settings.trace, GLOBAL_LOG_WARN)
				continue            
			}            
			if (postData) {
				traceEvent(settings.logFilter,"generateRemoteSensorEvents>adding ${data.thermostatList[0].remoteSensors[i]} to remoteData",settings.trace)
				remoteData << data.thermostatList[0].remoteSensors[i]  // to be transformed into Json later
			} 
			for (j in 0..data.thermostatList[0].remoteSensors[i].capability.size()-1) {
				traceEvent(settings.logFilter,"generateRemoteSensorEvents>looping i=${i},found ${data.thermostatList[0].remoteSensors[i].capability[j]} at j=${j}",settings.trace)
				if (data.thermostatList[0].remoteSensors[i].capability[j].type == REMOTE_SENSOR_TEMPERATURE) {
					if ((data.thermostatList[0].remoteSensors[i].capability[j].value==null) || 
						(!data.thermostatList[0].remoteSensors[i].capability[j].value.isInteger())) {
						traceEvent(settings.logFilter,"generateRemoteSensorEvents>looping i=${i},j=${j}; found temp value, not valid integer: ${data.thermostatList[0].remoteSensors[i].capability[j].value}",
							settings.trace, GLOBAL_LOG_WARN)                        
						continue
					}                    
					// Divide the sensor temperature by 10 
					value =(data.thermostatList[0].remoteSensors[i].capability[j].value.toFloat()/10).round(1)
 					remoteTempData = remoteTempData + data.thermostatList[0].remoteSensors[i].id + "," +
						data.thermostatList[0].remoteSensors[i].name + "," +
						data.thermostatList[0].remoteSensors[i].capability[j].type + "," + value.toString() + ",,"
					totalTemp = totalTemp + value
					maxTemp = Math.max(value,maxTemp)
					minTemp = (minTemp==null)? value: Math.min(value,minTemp)
					nbTempSensorInUse++
				} else if (data.thermostatList[0].remoteSensors[i].capability[j].type == REMOTE_SENSOR_HUMIDITY) {
					if ((data.thermostatList[0].remoteSensors[i].capability[j].value==null) || 
						(!data.thermostatList[0].remoteSensors[i].capability[j].value.isInteger())) {
						traceEvent(settings.logFilter,"generateRemoteSensorEvents>looping i=${i},j=${j}; found hum value, not valid integer: ${data.thermostatList[0].remoteSensors[i].capability[j].value}",
							settings.trace, GLOBAL_LOG_WARN)                        
						continue
					}                    
					remoteHumData = remoteHumData + data.thermostatList[0].remoteSensors[i].id + "," + 
						data.thermostatList[0].remoteSensors[i].name + "," +
						data.thermostatList[0].remoteSensors[i].capability[j].type + "," + data.thermostatList[0].remoteSensors[i].capability[j].value + ",,"
					value =data.thermostatList[0].remoteSensors[i].capability[j].value.toFloat()
					totalHum = totalHum + value
					maxHum = Math.max(value,maxHum)
					minHum = (minHum==null)? value: Math.min(value,minHum)
					nbHumSensorInUse++
				} else if (data.thermostatList[0].remoteSensors[i].capability[j].type == REMOTE_SENSOR_OCCUPANCY) {
					if (data.thermostatList[0].remoteSensors[i].capability[j].value==null) { 
						traceEvent(settings.logFilter,"generateRemoteSensorEvents>looping i=${i},j=${j}; found occ value, not valid: ${data.thermostatList[0].remoteSensors[i].capability[j].value}",
							settings.trace, GLOBAL_LOG_WARN)                        
						continue
					}                    
					remoteOccData = remoteOccData + data.thermostatList[0].remoteSensors[i].id + "," + 
						data.thermostatList[0].remoteSensors[i].name + "," +
						data.thermostatList[0].remoteSensors[i].capability[j].type + "," + data.thermostatList[0].remoteSensors[i].capability[j].value + ",,"
				} 
				                        
			} /* end for remoteSensor Capabilites */
		} /* end for remoteSensor data */
	}                        

	if (nbTempSensorInUse >0) {
		avgTemp = (totalTemp / nbTempSensorInUse).round(1)
		traceEvent(settings.logFilter,"generateRemoteSensorEvents>avgTemp for remote sensors= ${avgTemp},totalTemp=${totalTemp},nbTempSensors=${nbTempSensorInUse}", settings.trace)
	}                        
	def remoteSensorEvents = [
		remoteSensorOccData: "${remoteOccData.toString()}",
		remoteSensorAvgTemp: (avgTemp * 10),  // for display in the device, need to multiply by 10.
 		remoteSensorTmpData: "${remoteTempData.toString()}",
		remoteSensorMinTemp: ((minTemp!=null)? (minTemp *10):0),
		remoteSensorMaxTemp: (maxTemp *10)
	]    
	if (nbHumSensorInUse >0) {
		avgHum = (totalHum / nbHumSensorInUse).round()
		traceEvent(settings.logFilter,"generateRemoteSensorEvents>avgHum for remote sensors= ${avgHum},totalHum=${totalHum},nbHumSensors=${nbHumSensorInUse}",settings.trace)
		remoteSensorEvents = remoteSensorEvents + [remoteSensorHumData: "${remoteHumData.toString()}",remoteSensorAvgHumidity: avgHum,	
			remoteSensorMinHumidity: ((minHum!=null)?minHum:0),	remoteSensorMaxHumidity: maxHum]        
	}                        
	def remoteDataJson=""
 	if (remoteData != []) {
		remoteDataJson = new groovy.json.JsonBuilder(remoteData)
		remoteSensorEvents = remoteSensorEvents + [remoteSensorData: "${remoteDataJson.toString()}"]
	}
	traceEvent(settings.logFilter,"generateRemoteSensorEvents>remoteDataJson=${remoteDataJson}",settings.trace)
	traceEvent(settings.logFilter,"generateRemoteSensorEvents>remoteSensorEvents to be sent= ${remoteSensorEvents}",settings.trace)
	generateEvent(remoteSensorEvents)
}
    
    

//	if no thermostatId is provided, it is defaulted to the current thermostatId 
void getThermostatInfo(thermostatId,useCache=true) {

	thermostatId=determine_tstat_id(thermostatId)    

    String tstatlist=""
    int nTstats=0    
	int maxInd= (data?.thermostatCount)? (data?.thermostatCount-1) :0 
    for (i in 0..maxInd) {
		def thermostatDetails = data.revisionList[i].split(':')
		def Id = thermostatDetails[0]
		def thermostatName = thermostatDetails[1]
		def connected = thermostatDetails[2]
		if (connected == 'true') {
			if (nTstats == 0) {
				tstatlist = Id
				nTstats = 1
            } else {
    			tstatlist = tstatlist + "," + Id
	    		nTstats++
    		}
		}
    }
	traceEvent(settings.logFilter,"getThermostatInfo>found $nTstats connected to your ecobee account, list=$tstatlist", true, GLOBAL_LOG_INFO)
   	def thermostatInfo=parent.getThermostatInfo(tstatlist,useCache)
    if (thermostatInfo != null) {    
    	parent.updateObjects(this, "thermostat", thermostatId)   
		state?.retriesCounter=0            
//    	parent.updateObjects(this, "thermostat", thermostatId)   
		state?.retriesCounter=0            
        
    } else {
        
		state.exceptionCount = state.exceptionCount +1 
		state?.retriesCounter=(state?.retriesCounter?:0)+1            
		if (!(interval=get_exception_interval_delay( state?.retriesCounter, "GETTER"))) {  
			traceEvent(settings.logFilter,"getThermostatInfo>error max retries, counter=${state.retriesCounter}, exiting", true, GLOBAL_LOG_ERROR)
			state?.retriesCounter=0            
			return        
		}        
		data?.replayId=thermostatId
		traceEvent(settings.logFilter,"getThermostatInfo>about to call getThermostatInfoReplay,retries counter=${state.retriesCounter}, interval=$interval", true, GLOBAL_LOG_INFO)
		runIn(interval, "getThermostatInfoReplay",[overwrite:true])        
	}                  
    
}

void getThermostatInfoReplay() {
	def id = data?.replayId
	traceEvent(settings.logFilter,"getThermostatInfoReplay>about to recall getThermostatInfo for thermostatId=$id,retries counter=${state.retriesCounter}",
		true, GLOBAL_LOG_INFO)
	getThermostatInfo(id)
}


// tstatType =managementSet or registered (no spaces). 
// May also be set to a specific locationSet (ex./Toronto/Campus/BuildingA)
// thermostatId may be a single thermostat only
// returns true if intervalRevision, thermostatRevision or runtimeRevision has changed or false otherwise.

private boolean thermostat_revision_changed(tstatType, thermostatId) {	
	getThermostatRevision(tstatType, thermostatId)   
	def runtimeRevision=device.currentValue("runtimeRevision")
	def intervalRevision=device.currentValue("intervalRevision")
	def thermostatRevision=device.currentValue("thermostatRevision")
	traceEvent(settings.logFilter,"thermostat_revision_changed>done for ${thermostatId},intervalRevision=$intervalRevision,runtimeRevision=$runtimeRevision,thermostatRevision=$thermostatRevision," +
			"state.intervalRevision=${state?.intervalRevision},state.runtimeRevision=${state?.runtimeRevision},state.thermostatRevision=${state?.thermostatRevision}", settings.trace)
	if ((state?.runtimeRevision == runtimeRevision) &&
		(state?.intervalRevision == intervalRevision) &&
		(state?.thermostatRevision == thermostatRevision)) {
		traceEvent(settings.logFilter,"thermostat_revision_changed>same revisions, no changes at ecobee for thermostatId ${thermostatId}",settings.trace)
		return false
	} else {
		traceEvent(settings.logFilter,"thermostat_revision_changed>found revision changes at ecobee for thermostatId ${thermostatId} "  + 
				"state.intervalRevision=${state?.intervalRevision},state.runtimeRevision=${state?.runtimeRevision},state.thermostatRevision=${state?.thermostatRevision}",settings.trace)
		state?.intervalRevision=intervalRevision
		state?.runtimeRevision=runtimeRevision
		state?.thermostatRevision=thermostatRevision
		return true
	}            
}
// tstatType =managementSet or registered (no spaces). 
// May also be set to a specific locationSet (ex./Toronto/Campus/BuildingA)
// thermostatId may be a single thermostat only
void getThermostatRevision(tstatType, thermostatId) {
	def runtimeRevision,intervalRevision,thermostatRevision
    
	thermostatId = determine_tstat_id(thermostatId)
	tstatType = determine_ecobee_type_or_location(tstatType)
	getThermostatSummary(tstatType)
	int maxInd= (data?.thermostatCount)? (data?.thermostatCount-1) :0 
    for (i in 0..maxInd) {
		def thermostatDetails = data.revisionList[i].split(':')
		def id = thermostatDetails[0]
		def thermostatName = thermostatDetails[1]
		def connected = thermostatDetails[2]
		thermostatRevision = thermostatDetails[3]
		runtimeRevision = thermostatDetails[5]
		intervalRevision = thermostatDetails[6]
		if ((thermostatId == id) && (connected=='true')) {
			sendEvent name: "runtimeRevision", value: runtimeRevision
			sendEvent name: "intervalRevision", value: intervalRevision
			sendEvent name: "thermostatRevision", value: thermostatRevision
			traceEvent(settings.logFilter,"getThermostatRevision>done for ${thermostatId},intervalRevision=$intervalRevision,runtimeRevision=$runtimeRevision,thermostatRevision=$thermostatRevision",
				settings.trace)            
			
		} else if ((thermostatId == id) && (connected=='false')) {
			traceEvent(settings.logFilter,"getThermostatRevision>thermostatId ${id} not connected",true, GLOBAL_LOG_WARN)
		}       
	} /* end for */   
}
// tstatType =managementSet or registered (no spaces). 
// May also be set to a specific locationSet (ex./Toronto/Campus/BuildingA)
void getThermostatSummary(tstatType, useCache=true) {
	def ECOBEE_NEED_TOKEN_REFRESH=14
	def TOKEN_EXPIRED=401   
	def interval    
    
	def thermostatId=determine_tstat_id("")    
	tstatType = determine_ecobee_type_or_location(tstatType)

	def thermostatSummary=parent.getEcobeeThermostats(tstatType, useCache)
    if (thermostatSummary != null) {    
		state?.retriesCounter=0            
		parent.updateStructure(this)
        traceEvent(settings.logFilter,"getThermostatSummary>summary= $thermostatSummary, thermostatId=${thermostatId},revision data=${data?.revisionList}",settings.trace)
        traceEvent(settings.logFilter,"getThermostatSummary>thermostatId=${thermostatId},status data=${data?.statusList}",settings.trace)
 
        for (i in 0..(data.thermostatCount - 1)) {
    		def thermostatDetails = data.revisionList[i].split(':')
	    	def id = thermostatDetails[0]
    		def thermostatName = thermostatDetails[1]
	    	def connected = thermostatDetails[2]
    		def thermostatRevision = thermostatDetails[3]
	    	def alertRevision = thermostatDetails[4]
    		def runtimeRevision = thermostatDetails[5]
	    	def intervalRevision = thermostatDetails[6]
    		traceEvent(settings.logFilter,"getThermostatSummary>thermostatId=${id},name=${thermostatName},connected =${connected}",settings.trace)
	    	traceEvent(settings.logFilter,"getThermostatSummary>intervalRevision=${intervalRevision},runtimeRevision=${runtimeRevision},thermostatRevision=${thermostatRevision}",
				settings.trace)                    
            if (thermostatId==id) {		
                def isChange = isStateChange(device, "connected", connected)
    	    	sendEvent(name: "connected", value: connected,  isStateChange: isChange, displayed:(settings.trace?:false), descriptionText: "connected state ($connected) according to ecobee")                
            }            
	    } /* end for */
    } else {
		state.exceptionCount = state.exceptionCount +1 
		state?.retriesCounter=(state?.retriesCounter?:0)+1            
		if (!(interval=get_exception_interval_delay( state?.retriesCounter, "GETTER"))) {  
			traceEvent(settings.logFilter,"getThermostatSummary>error max retries, counter=${state.retriesCounter}, exiting", true, GLOBAL_LOG_ERROR)
			state?.retriesCounter=0            
			return        
		}        
		data?.replayEcobeeType=tstatType
		traceEvent(settings.logFilter,"getThermostatSummary>about to call getThermostatSummaryReplay,retries counter=${state.retriesCounter},interval=$interval", true, GLOBAL_LOG_INFO)
		runIn(interval, "getThermostatSummaryReplay",[overwrite:true])        
        
    }        
	traceEvent(settings.logFilter,"getTstatSummary>done",settings.trace)
}

void getThermostatSummaryReplay() {
	def id = data?.replayEcobeeType
	traceEvent(settings.logFilter,"getThermostatSummaryReplay>about to recall getThermostatSummary for ecobeeType=$id,retries counter=${state.retriesCounter}",
		true, GLOBAL_LOG_INFO)
	getThermostatSummary(id)
}

private void reset_replay_data(replayBuffer) { 
	for (int i=1; (i<= get_MAX_SETTER_RETRIES()); i++) 
		data?."replay${replayBuffer}Id${i}"= null
}

private int get_exception_interval_delay(counter,method="SETTER") {

	int max_retries=(method=="SETTER")? get_MAX_SETTER_RETRIES() :get_MAX_GETTER_RETRIES()
	counter=(!counter)?(max_retries+1):counter
	int interval = 1*60 * (counter as int) // the interval delay will increase if multiple retries have already been made

	if (counter > max_retries) {
		traceEvent(settings.logFilter,"get_exception_interval_delay>error max retries ($max_retries), counter=${counter}, exiting", settings.trace, GLOBAL_LOG_WARN)
		return 0
	}        
	if (counter>=5) {
		interval=(get_RETRY_DELAY_FACTOR() *  counter * 60)  // increase delay even more when number of retries >5            
	}            
	if (counter>=7) {
		interval=interval + (get_RETRY_DELAY_FACTOR() * counter * 60)  // increase delay even more when number of retries >7           
	}            
	return interval    
}


// poll(), refresh() or getThermostatInfo() must be called prior to calling the getModelNumber() method 
// Return thermostat's current Model Number */
private def getModelNumber() {

//	traceEvent(settings.logFilter,"getModelNumber>thermostatId=${data.thermostatList[0]?.identifier}," +
//        	"modelNumber=${data.thermostatList[0]?.modelNumber}", settings.trace)
	return (device.getCurrentValue("thermostatId") ? device.getCurrentValue("modelNumber"): "")
}

private def refreshLocalAuthToken() {	
	boolean refresh_success=false
	def REFRESH_SUCCESS_CODE=200    
 
	traceEvent(settings.logFilter,"refreshLocalAuthToken>about to refresh auth token", settings.trace)
    
	def stcid = get_appKey()

	def refreshParams = [
			uri   : "${get_URI_ROOT()}",
			path  : "/token",
			query: []            
		]
	if (data.auth?.jwt) {
			refreshParams?.query = [
				grant_type:    "refresh_token",
				refresh_token:	data.auth.refresh_token,
				client_id :		stcid,
				ecobee_type:   "jwt"
			]
			traceEvent(settings.logFilter,"refreshLocalAuthToken>refreshParams=jtw present, refreshParams= $refreshParams", settings.trace)
	} else {
			refreshParams?.query = [
				query : [grant_type: 'refresh_token', code: "${data.auth.refresh_token}", client_id: stcid]
			]
			traceEvent(settings.logFilter,"refreshLocalAuthToken>refreshParams=jtw not present, refreshParams= $refreshParams", settings.trace)
	}

	traceEvent(settings.logFilter,"refreshLocalAuthToken>refreshParams=$refreshParams", settings.trace)
	httpPost(refreshParams) { resp ->
		if (resp?.status == REFRESH_SUCCESS_CODE) {
			traceEvent(settings.logFilter,"refreshLocalAuthToken>token refresh done resp = ${resp?.data}",settings.trace)

			if (resp.data) {

				data.auth.access_token = resp.data.access_token
				data.auth.refresh_token = resp.data.refresh_token
				data.auth.expires_in = resp.data.expires_in
				data.auth.token_type = resp.data.token_type
				data.auth.scope = resp.data.scope
				def authexptime = new Date((now() + (resp?.data?.expires_in  * 1000))).getTime()
				data.auth.authexptime=authexptime 						                        
				traceEvent(settings.logFilter,"refreshLocalAuthToken>new authexptime = ${authexptime}",settings.trace)
				traceEvent(settings.logFilter,"refreshLocalAuthToken>new access token = ${data.auth.access_token}",settings.trace)
				traceEvent(settings.logFilter,"refreshLocalAuthToken>new refresh token = ${data.auth.refresh_token}",settings.trace)
				refresh_success=true 
				if (!isTokenExpired()) {    
					parent.setParentAuthTokens(data.auth)
				}
	               
			} /* end if resp.data */
		} else {
			traceEvent(settings.logFilter,"refreshLocalAuthToken>refresh failed ${resp.status} : ${resp?.status}",true, GLOBAL_LOG_ERROR)
		} /* end if http status == 200 */
	} /* end if http post */           

	return refresh_success	        
}

private def refresh_tokens() {
	boolean refresh_from_child=false
	if (data.auth?.thermostatId) {  
		if (isTokenExpired()) {    
	 		traceEvent(settings.logFilter,"refresh_tokens>trying to get tokens from parent first to avoid repetitive calls to parent.refreshParentTokens()")
 			parent.refreshThisChildAuthTokens(this) 
			if (!isTokenExpired()) {    
				state?.retriesRefreshCounter=0          
				state?.lastRefreshTimestamp= now()
				return true
			}
		} else if (parent.isTokenExpired()) {
			parent.setParentAuthTokens(data.auth)			        
		}        
	}    
	state?.retriesRefreshCounter=((state?.retriesRefreshCounter)?:0 as Integer)+1            
	def buffer_time_refresh=(get_exception_interval_delay(state?.retriesRefreshCounter, "GETTER"))?: get_MAX_REFRESH_TOKENS_DELAY()  

	long time_check_for_refresh = (now() - (buffer_time_refresh * 1000))
    
	if ((state?.lastRefreshTimestamp) && (state?.lastRefreshTimestamp > time_check_for_refresh)) {
		traceEvent(settings.logFilter,"refresh_tokens>time_check_for_refresh (${time_check_for_refresh} < state.lastRefreshTimestamp (${state?.lastRefreshTimestamp}), not refreshing tokens now, need to wait...",
			settings.trace, GLOBAL_LOG_TRACE)        
		return false
	}
    
//	if device created by initialSetup (Service Manager)

	if (data.auth?.thermostatId) {
		if (isTokenExpired())  { 	
			if (!parent.refreshParentTokens()) {
				state?.lastRefreshTimestamp= now()
				traceEvent(settings.logFilter,"refresh_tokens>warning: local tokens still expired after refreshParentTokens() call", settings.trace)
				if ((state?.retriesRefreshCounter as int) >=  get_MAX_GETTER_RETRIES()) {
					runIn((1*60),"refreshLocalAuthToken")// If not successful, try to refresh the device tokens locally
					traceEvent(settings.logFilter,"refresh_tokens>warning: called refreshLocalAuthToken() after parent call failure",settings.trace, GLOBAL_LOG_WARN)
					state?.retriesRefreshCounter=0 // to alternate between parent and child tokens refresh logic                 
					refresh_from_child=true                        
				}                    
			} /* end if  */                
		}            
		if (isTokenExpired() && (!refresh_from_child)) {  // check again if tokens still expired after refreshParentTokens call 
			traceEvent(settings.logFilter,"refresh_tokens>local tokens still expired after call to refreshParentTokens,trying to get tokens from parent again",settings.trace)
			parent.refreshThisChildAuthTokens(this) 
			state?.lastRefreshTimestamp= now()
		}  	
	}  else {  /* standalone device with PIN */
		refreshLocalAuthToken()
	}    
	if (!isTokenExpired()) {    
		state?.lastRefreshTimestamp= now()
		state?.retriesRefreshCounter=0   
		return true
	}  	        
	traceEvent(settings.logFilter,"refresh_tokens>local tokens still expired after refresh_tokens() call",settings.trace, GLOBAL_LOG_WARN)
	return false
}
private def synchronized refreshChildTokens(auth) {
	traceEvent(settings.logFilter,"refreshChildTokens>current data.auth= $data.auth",settings.trace)
	if ((!data?.auth?.authexptime) || ((data?.auth?.authexptime) && (auth.authexptime.toLong() > data?.auth?.authexptime.toLong()))) { 
		if (!data?.auth?.authexptime) { // if info lost
			def varSettings=[:]
			varSettings=settings  
			data?.auth=varSettings            
			if (!settings.appKey) { // if appKey is lost
				settings.appKey=auth?.clientId
			}            
		}            
		save_data_auth(auth)            
		traceEvent(settings.logFilter,"refreshChildTokens>saved new token auth= $auth",settings.trace)
	}  
	if (!isTokenExpired()) {
		return true   
	}       
	traceEvent(settings.logFilter,"refreshChildTokens>warning:tokens still expired")
	return false    
}

private void save_data_auth(auth) {

	data?.auth?.access_token = auth.authToken
	data?.auth?.refresh_token = auth.refreshToken
	data?.auth?.expires_in = auth.expiresIn
	data?.auth?.token_type = auth.tokenType
	data?.auth?.scope = auth.scope
	data?.auth?.jwt=auth?.jwt    
	data?.auth?.authexptime = auth.authexptime
	traceEvent(settings.logFilter,"save_data_auth>saved data.auth=$data.auth")
}

private void login() {
	traceEvent(settings.logFilter,"login> about to call setAuthTokens",settings.trace)
	if (data?.auth?.thermostatId) {
    	// Created by initalSetup
		traceEvent(settings.logFilter,"login>should be already logged in...",true, GLOBAL_LOG_ERROR)
		parent.refreshThisChildAuthTokens(this) 
        
	} else { 
		setAuthTokens()
	}    
	if (!isLoggedIn()) {
		traceEvent(settings.logFilter,"login> no auth_token..., failed",true, GLOBAL_LOG_ERROR)
		return
	}
}

void getEcobeePinAndAuth() {
	String SCOPE = "smartWrite,ems"
	def method = 
	[
		headers: [
			'Content-Type': "application/json",
			'charset': "UTF-8"
			],
		uri: "${get_URI_ROOT()}/authorize?" +
		"response_type=ecobeePin&" +
		"client_id=${get_appKey()}&" +
		"scope=${SCOPE}"
	]
	def successEcobeePin = {resp ->
		traceEvent(settings.logFilter,"getEcobeePinAndAuth> response = ${resp.data}",settings.trace)
		data?.auth = resp.data
		data.auth.code = resp.data.code
		data.auth.expires_in = resp.data.expires_in
		data.auth.interval = resp.data.interval
		data.auth.ecobeePin = resp.data.ecobeePin
		traceEvent(settings.logFilter,"getEcobeePin>${data.auth.ecobeePin}", settings.trace)
		data.auth.access_token = null // for isLoggedIn() later
		data.thermostatCount = null // for iterate functions later
		data.groups = null // for iterateUpdateGroups later
	}
	try {
		httpGet(method, successEcobeePin)
	} catch (java.net.UnknownHostException e) {
		traceEvent(settings.logFilter,"getEcobeePinAndAuth>Unknown host - check the URL " + method.uri,true, GLOBAL_LOG_ERROR)
		return
	} catch (java.net.NoRouteToHostException e) {
		traceEvent(settings.logFilter,"getEcobeePin> No route to host " +	method.uri, true, GLOBAL_LOG_ERROR)
		return
	} catch (java.io.IOException e) {
		traceEvent(settings.logFilter,"getEcobeePinAndAuth> Authentication error, bad URL or settings missing " +
			method.uri, true, GLOBAL_LOG_ERROR)
		return
	} catch (any) {
		traceEvent(settings.logFilter,"getEcobeePin> general error " +	method.uri, true, GLOBAL_LOG_ERROR)
		return
	}
	traceEvent(settings.logFilter,"getEcobeePinAndAuth> ecobeePin= ${data.auth.ecobeePin}, authorizationCode=${data.auth.code},scope=${data.auth.scope} expires_in=${data.auth.expires_in} done",
		settings.trace)
}

void setAuthTokens() {
	def method = 
	[
		headers: [
			'X-nl-protocol-version': 1,
			'Content-Type': "application/json",
			'charset': "UTF-8"
			],
		uri: "${get_URI_ROOT()}/token?" +
		"grant_type=ecobeePin&" +
		"code=${data.auth.code}&" +
		"client_id=${get_appKey()}"
	]
	if (data?.auth?.access_token == null) {
		def successTokens = {resp ->
			data.auth.access_token = resp.data.access_token
			data.auth.refresh_token = resp.data.refresh_token
			data.auth.expires_in = resp.data.expires_in
			data.auth.token_type = resp.data.token_type
			data.auth.scope = resp.data.scope
			traceEvent(settings.logFilter,"setAuthTokens> accessToken= ${data.auth.access_token}, refreshToken=${data.auth.refresh_token}," +
					"tokenType=${data.auth.token_type},scope=${data.auth.scope}",settings.trace)
		}
		try {
			traceEvent(settings.logFilter,"setAuthTokens> about to call httpPost with code= ${data.auth.code}",settings.trace)
			httpPostJson(method, successTokens)

		} catch (java.net.UnknownHostException e) {
			traceEvent(settings.logFilter,"setAuthTokens> Unknown host " + method.uri, true, GLOBAL_LOG_ERROR)
			return
		} catch (java.net.NoRouteToHostException t) {
			traceEvent(settings.logFilter,"setAuthTokens> No route to host" +	method.uri, true, GLOBAL_LOG_ERROR)
			return
		} catch (java.io.IOException e) {
			traceEvent(settings.logFilter,"setAuthTokens> Auth error " + method.uri, true, GLOBAL_LOG_ERROR)
			return
		} catch (any) {
			traceEvent(settings.logFilter,"setAuthTokens>general error " + method.uri, true, GLOBAL_LOG_ERROR)
			return
		}
		// determine token's expire time
		def authexptime = new Date((now() + (data.auth.expires_in  * 1000))).getTime()
		data.auth.authexptime = authexptime
		traceEvent(settings.logFilter,"setAuthTokens> expires in ${data.auth.expires_in} seconds", settings.trace)
		traceEvent(settings.logFilter,"setAuthTokens> data_auth.expires_in in time = ${authexptime}",settings.trace)
	}
}
private def isLoggedIn() {
	if (data?.auth?.access_token == null) {
			traceEvent(settings.logFilter,"isLoggedIn> no access token", true, GLOBAL_LOG_WARN)
			return false
	}
	return true
}
private def isTokenExpired() {
	def buffer_time_expiration=5  // set a 5 min. buffer time 
	def time_check_for_exp = now() + (buffer_time_expiration * 60 * 1000)
/*
    if (!data?.auth?.authexptime) {
		login()    
	}    
*/
	double authExpTimeInMin= ((data?.auth?.authexptime - time_check_for_exp)/60000).toDouble().round(0)  
	traceEvent(settings.logFilter,"isTokenExpired>expiresIn timestamp: ${data.auth.authexptime} > timestamp check for exp: ${time_check_for_exp}?",settings.trace)
	traceEvent(settings.logFilter,"isTokenExpired>expires in ${authExpTimeInMin.intValue()} minutes",settings.trace)
    traceEvent(settings.logFilter,"isTokenExpired>data.auth= ${data.auth}",settings.trace)
	if (authExpTimeInMin <0) {
		traceEvent(settings.logFilter,"isTokenExpired>auth token buffer time  expired (${buffer_time_expiration} min.), countdown is ${authExpTimeInMin.intValue()} minutes, need to refresh tokens now!",
			settings.trace, GLOBAL_LOG_WARN)        
	}    
	if (authExpTimeInMin < (0-buffer_time_expiration)) {
		traceEvent(settings.logFilter,"isTokenExpired>refreshing tokens is more at risk (${authExpTimeInMin} min.),exception count may increase if tokens not refreshed!", settings.trace, GLOBAL_LOG_WARN)
	}    
	if (data.auth.authexptime.toLong() > time_check_for_exp.toLong()) {
		traceEvent(settings.logFilter,"isTokenExpired> not expired...", settings.trace)
		return false
	}
	traceEvent(settings.logFilter,"isTokenExpired> expired...", settings.trace)
	return true
}

private def determine_ecobee_type_or_location(tstatType) {
	def ecobeeType
    def modelNumber    
    try {
         modelNumber = getModelNumber()
    } catch (e) {}
    
	traceEvent(settings.logFilter,"determine_ecobee_type>begin ecobeeType = ${tstatType}, modelNumber=${modelNumber}",settings.trace)
	if ((tstatType != null) && (tstatType != "")) {
		ecobeeType = tstatType.trim()
	} else if ((settings?.ecobeeType != null) && (settings?.ecobeeType != "")) {
		ecobeeType = settings.ecobeeType.trim()
		
	} else if (((modelNumber != "") && ((modelNumber != null)) && (modelNumber.toUpperCase().contains("EMS")))) {
    
		ecobeeType = 'managementSet'
		settings.ecobeeType='managementSet'
    
	} else {
		// by default, the ecobee type is 'registered'
		ecobeeType = 'registered'
		settings.ecobeeType='registered'
	}
	traceEvent(settings.logFilter,"determine_ecobee_type>end ecobeeType = ${ecobeeType}",settings.trace)
	return ecobeeType
}

// Determine id from settings or initalSetup
private def determine_tstat_id(tstat_id) {
	def tstatId=device.currentValue("thermostatId")
    
	if ((tstat_id != null) && (tstat_id != "")) {
		tstatId = tstat_id
	} else if ((settings.thermostatId != null) && (settings.thermostatId  != "")) {
		tstatId = settings.thermostatId.trim()
		traceEvent(settings.logFilter,"determine_tstat_id> tstatId = ${settings.thermostatId}", settings.trace)
	} else if (data?.auth?.thermostatId) {
		settings.appKey = data.auth.appKey
		settings.thermostatId = data.auth.thermostatId
		tstatId=data.auth.thermostatId
		traceEvent(settings.logFilter,"determine_tstat_id> tstatId from data.auth= ${data.auth.thermostatId}",settings.trace)
	} else if ((tstatId !=null) && (tstatId != "")) {
		settings.thermostatId = tstatId
		traceEvent(settings.logFilter,"determine_tstat_id> tstatId from device= ${tstatId}",settings.trace)
	}
    
	if ((tstat_id != "") && (tstatId && tstat_id != tstatId)) {
		sendEvent(name: "thermostatId", displayed: (settings.trace?:false),value: tstatId)    
	}
	return tstatId
}

// Get the appKey for authentication
private def get_appKey() {
	if (settings.appKey) {
		return settings.appKey
	} else {
		return data.auth.appKey    
	}    
    
}    

// Called by My ecobee Init for initial creation of a child Device
void initialSetup(device_client_id, auth_data, device_tstat_id) {
	settings.trace=true
    settings.logFilter=5    
	traceEvent(settings.logFilter,"initialSetup>begin",settings.trace)
	traceEvent(settings.logFilter,"initialSetup> device_tstat_Id = ${device_tstat_id}",settings.trace)
	traceEvent(settings.logFilter,"initialSetup> device_client_id = ${device_client_id}",settings.trace)
	settings.appKey= device_client_id
	settings.thermostatId = device_tstat_id

	data?.auth = [:]
	data.auth = settings
	save_data_auth(auth_data)
	traceEvent(settings.logFilter,"initialSetup> settings = $settings",settings.trace)
	traceEvent(settings.logFilter,"initialSetup> state_auth = $data.auth",settings.trace)
	traceEvent(settings.logFilter,"initialSetup>end",settings.trace)

	runIn(1*60,"refresh")    
//  refresh()    
//	def ecobeeType=determine_ecobee_type_or_location("")
	data.auth.ecobeeType = ecobeeType
	state?.exceptionCount=0    
	state?.scale = getTemperatureScale()
//	state?.currentMode=device.currentValue("thermostatMode")  
    
}

void produceSummaryReport(pastDaysCount) {
	traceEvent(settings.logFilter,"produceSummaryReport>begin",settings.trace, GLOBAL_LOG_TRACE)
	def avg_tstat_temp,avg_room_setpoint, min_room_setpoint=200, max_room_setpoint=0, avg_tstat_humidity    
	boolean found_values=false
	Date todayDate = new Date()
	Date startOfPeriod = todayDate - pastDaysCount
	long min_room_timestamp,max_room_timestamp
	int holdCount, scheduleCount
    
	def rmSetpointData = device.statesSince("thermostatSetpoint", startOfPeriod, [max:200])
	def temperatureData = device.statesSince("temperature", startOfPeriod, [max:200])
	def humidityData = device.statesSince("humidity", startOfPeriod, [max:200])
	def holdEvents= device.statesSince("programScheduleName", startOfPeriod, [max:500])
	if (rmSetpointData) {    
		avg_room_setpoint =  (rmSetpointData.sum{it.floatValue.toFloat()}/ (rmSetpointData.size())).toFloat().round(1)
		        
		int maxInd=rmSetpointData?.size()-1    
		for (int i=maxInd; (i>=0);i--) {
			if (rmSetpointData[i]?.floatValue.toFloat() < min_room_setpoint.toFloat()) {
				min_room_setpoint=rmSetpointData[i]?.floatValue   
				min_room_timestamp=rmSetpointData[i]?.date.getTime()                
			}
			if (rmSetpointData[i]?.floatValue.toFloat() > max_room_setpoint.toFloat()) {
				max_room_setpoint=rmSetpointData[i]?.floatValue   
				max_room_timestamp=rmSetpointData[i]?.date.getTime()                
			}
		}            
		found_values=true        
	} 
    
	if (temperatureData) {    
		avg_tstat_temp= (temperatureData.sum{it.floatValue.toFloat()}/ (temperatureData.size())).toFloat().round(1)
		found_values=true        
	}        
	if (humidityData) {    
		avg_tstat_humidity = (humidityData.sum{it.value.toInteger()}/ (humidityData.size())).toFloat().round(0)
		found_values=true        
	} 
	if (holdEvents) {    
		holdCount= holdEvents.count{it.value.toString().contains('hold')}
		scheduleCount= holdEvents.count{((!it.value.toString().contains('hold')) && (!it.value.toString().contains('auto')))}
		found_values=true        
	}    
	if (!found_values) {
		traceEvent(settings.logFilter,"produceSummaryReport>found no values for report,exiting",settings.trace)
		sendEvent(name: "summaryReport", value: "")
		return        
	}    
	String scale=getTemperatureScale(), unitScale='Fahrenheit',timePeriod="In the past ${pastDaysCount} days"
	def homeAwayMode= (device.currentValue("presence") != 'present') ?  'away' : 'present'
	if (scale=='C') { 
		unitScale='Celsius'    
	}    
	if (pastDaysCount <2) {
		timePeriod="In the past day"    
	}    
	String currentMode=device.currentValue("thermostatMode")    
	String summary_report = "At your home, your ecobee's is currently in $currentMode mode, and its presence sensor is set to ${homeAwayMode}." 
	summary_report=summary_report + "${timePeriod}"    
    
	summary_report= summary_report + ",at the ${device.displayName} thermostat"
    
	if (holdCount) {
 		summary_report= summary_report + ",there were $holdCount hold(s) observed" 
 	}
	if (scheduleCount) {
 		summary_report= summary_report + ",there were $scheduleCount schedule change(s)" 
 	}
    
	if (avg_room_temp) {
		summary_report= summary_report + ",the average room temperature was ${avg_room_temp.toString()} degrees ${unitScale}"
	}
    
	if (avg_room_setpoint) {
 		summary_report= summary_report + ",your thermostat's setpoint was ${avg_room_setpoint.toString()} degrees in average" 
 	}
	if (min_room_setpoint && (min_room_timestamp != max_room_timestamp)) {
		def timeInLocalTime= formatTimeInTimezone(min_room_timestamp)					    
		summary_report= summary_report + ".The Ecobee's minimum setpoint was ${min_room_setpoint.toString()} degrees on ${timeInLocalTime.substring(0,16)}" 
	}
	if (max_room_setpoint && (min_room_timestamp != max_room_timestamp)) {
		def timeInLocalTime= formatTimeInTimezone(max_room_timestamp)					    
		summary_report= summary_report + ",and the Ecobee's maximum setpoint was ${max_room_setpoint.toString()} degrees on ${timeInLocalTime.substring(0,16)}" 
	}
    
	if (avg_tstat_temp) {
		summary_report= summary_report + ".The Ecobee's average ambient temp collected was ${avg_tstat_temp.toString()} degrees ${unitScale}."
	}
    
	if (avg_tstat_humidity) {
		summary_report= summary_report + "And finally, the thermostat's average relative humidity observed was ${avg_tstat_humidity.toString()}%."      
	}

	sendEvent(name: "summaryReport", value: summary_report, isStateChange: true)
    
	traceEvent(settings.logFilter,"produceSummaryReport>end",settings.trace, GLOBAL_LOG_TRACE)

}


private def get_stats_attribute(attribute, startDate,endDate,operation='avg',filterEvents=false,value=null) {
	def result
	int count    
	def recentStates = device.statesBetween(attribute, startDate, endDate, [max:200])
	def MIN_TEMP_VALUE=0
	def MAX_TEMP_VALUE=120
    
	if ((!value) && filterEvents) {    
		count =recentStates.count {(it.floatValue >MIN_TEMP_VALUE && it.floatValue < MAX_TEMP_VALUE)}
	} else if (!value) {    
		count =recentStates.count {it.value}
	} else {
		count =recentStates.count {(it.value == value)}    
	}    
	switch (operation) {    
		case 'total':
			if (filterEvents) {             
				result =recentStates.inject(0) { total, i -> total + ((i.floatValue >MIN_TEMP_VALUE && i.floatValue < MAX_TEMP_VALUE)? i.floatValue :0) }
			} else {
				result =recentStates.inject(0) { total, i -> total + i.floatValue }
	
    		}
			break        
		case 'avg':
		case 'deviation':
			float avgResult        
			if (filterEvents) {             
				avgResult = recentStates.inject(0) { total, i -> total + ((i.floatValue >MIN_TEMP_VALUE && i.floatValue < MAX_TEMP_VALUE)? i.floatValue:0)}
			} else {
				avgResult = recentStates.inject(0) { total, i -> total + i.floatValue}
			}            
			avgResult = (count)? (avgResult /count).round(1):0        
			if (operation == 'avg') {
				result=avgResult            
				break
			}   
			if (filterEvents) {             
				result = recentStates.inject(0) { totalDev, i -> totalDev + ((i.floatValue >MIN_TEMP_VALUE && i.floatValue < MAX_TEMP_VALUE)? 
					((i.value.toFloat() - avgResult).abs()) : 0)}
			} else {  
				result = recentStates.inject(0) { totalDev, i -> totalDev + ((i.floatValue - avgResult).abs())}
			}			                    
			result = (count)? (result /count).round(2):0        
//			traceEvent(settings.logFilter,"get_stats_attribute>for ${attribute}, avg=${avgResult},avgDeviation=${result},count=${count}",settings.trace)
			break            
		case 'min':
			if (filterEvents) {             
				result =recentStates.inject(MAX_TEMP_VALUE) {min, i -> Math.min(min,((i.floatValue >MIN_TEMP_VALUE && i.floatValue < MAX_TEMP_VALUE)?i.floatValue:MAX_TEMP_VALUE))}
			} else {
				result =recentStates.inject(MAX_TEMP_VALUE) {min, i -> Math.min(min, i.floatValue) }
			}            
			break            
		case 'max':
			if (filterEvents) {             
				result =recentStates.inject(MIN_TEMP_VALUE) {max, i -> Math.max(max,((i.floatValue >MIN_TEMP_VALUE && i.floatValue < MAX_TEMP_VALUE)?i.floatValue:MIN_TEMP_VALUE)) }
			} else {
				result =recentStates.inject(MIN_TEMP_VALUE) {max, i -> Math.max(max,i.floatValue) }
			}            
			break            
		case 'values':
        
			if (filterEvents) {             
				result =recentStates.inject([]) {valueSet, i -> (i.floatValue >MIN_TEMP_VALUE && i.floatValue < MAX_TEMP_VALUE)?
					valueSet << i.floatValue: {} }
			} else if (value) {
				def valuesFound =recentStates.find {it.value==value }
				result = valuesFound.inject([])  {valueSet, i -> valueSet << i.value}               
                
			} else {                 
				result =recentStates.inject([]) {valueSet, i -> valueSet << i.value}
			}                
			                
			break            
		case 'dates':
			if (filterEvents) {             
				result =recentStates.inject([]) {valueSet, i -> (i.floatValue >MIN_TEMP_VALUE && i.floatValue < MAX_TEMP_VALUE)?
					valueSet << i.date.getTime(): {} }
			} else if (value) {
				def valuesFound =recentStates.find {it.value==value }
//				if (settings.trace) {                
//					valuesFound.each {                
//						traceEvent(settings.logFilter,"get_stats_attribute>dates valueFound= ${it.value}, date=${it.date.getTime()}, true)
//					}                        
//				}                    
				result = valuesFound.inject([])  {valueSet, i -> valueSet << i.date.getTime()}               
//				traceEvent(settings.logFilter,"get_stats_attribute>dates result set= ${result}",settings.trace)
			} else {                 
				result =recentStates.inject([]) {valueSet, i ->  valueSet << i.date.getTime()}
			}                
			break            
		default:
			result=count
		break            
	}    
	return result    
}

private def get_recommendation(tip) {

	def tips = [
		'tip01': 
			[level:1, 
				text:"You should set the default Sleep climate at ecobee according to your Sleep schedule and use energy efficient settings as indicated on the energy.gov website." +
				"You can save as much as 10% to 20% a year on heating and cooling by doing a temperature setback or setforward of 7 degrees Fahrenheit or 3 degrees Celsius for 8 hours a day from its normal setting." +
				"A temperature setback is when you lower your desired temperature before your thermostat calls for heat." +
				"A temperature setforward is when you increase your desired temperature before your thermostat calls for cool."
			], 
		'tip02': 
			[level:1, 
				text:"You should set the default Away climate at ecobee according to your work schedule and use energy efficient settings as indicated on the energy.gov website." +
				"You can save as much as 10% to 20% a year on heating and cooling by doing a temperature setback or setforward of 7 degrees Fahrenheit or 3 degrees Celsius for 8 hours a day from its normal setting." +
				"A temperature setback is when you lower your desired temperature before your thermostat calls for heat." +
				"A temperature setforward is when you increase your desired temperature before your thermostat calls for cool."
			],
		'tip03': 
			[level:1, 
 				text: "When you have more than one thermostat registered to your account, you can group them together. Adjustments made on one thermostat are then made to ALL thermostats in that group. It will allow you to determine which features within a specific group are synchronized. " +
				"For example if you select Synchronize User Preferences, when you configure any user preference on one thermostat (i.e. choose Celsius vs. Fahrenheit) ALL thermostats within that group will change to Celsius.  Consult your ecobee user guide for more details."			
			],
		'tip04': 
			[level:1, 
				text: "To reduce the number of cycles, you can try to increase the Heat and Cool Minimum On Time and the Heat and Cool Differential settings at the thermostat unit in Main Menu, Settings, Installation Settings, and finally Thresholds for longer cycles." +
				"You can also try to increase the Heat or Cool dissipation time"  +
				" at the thermostat unit, which is 30 seconds by default. The dissipation time settings control the fan runtime in seconds after a cool or heat cycle which helps to evacuate any hot or cool air from the ducts." + 
				" Consult the ecobee user guide for more details."
			],
		'tip05': 
			[level:1, 
				text:"The default Hold action is “Until you change it.” You can configure how long a temperature hold will remain in effect. On the thermostat, Select Main Menu, Settings,Preferences, then Select Hold action."+
				"Your options are: a) 2 hours (Holds temperature change for a period of 2 hours, then resumes your normal schedule)." +
				"b) 4 hours, c) until the next scheduled activity or comfort setting and the last one d) Until you change it. For maximum efficiency, it is recommended to use the 'until the next scheduled activity or transition' as ecobee will use its own scheduling as much as possible and avoid permanent holds." 
			],
		'tip06': 
			[level:1, 
				text:"Access Control uses a 4-digit security code to prevent people, such as guests and children, from making changes to your thermostat settings. " +
				"To enable Access Control on the Thermostat, select Main Menu, Settings, Access Control, Enable Security Code, Enter a New Code, and finally Save."			
			],
		'tip10': 
			[level:2, 
				text:"You can save as much as 10% to 20% a year on heating and cooling by doing a temperature setback or setforward of 7 degrees Fahrenheit or 3 degrees Celsius for 8 hours a day from its normal setting." 
			],
		'tip13': 
			[level:2, 
				text:"The location of your thermostat and remote sensors (if any) can affect its performance and efficiency. Read the manufacturer's installation instructions to prevent ghost readings or unnecessary furnace or air conditioner cycling." +
				"To operate properly, the thermostat and any remote sensor must be on an interior wall away from direct sunlight, drafts, doorways, skylights, and windows. It should be located where natural room air currents–warm air rising, cool air sinking–occur." +
				"Also, make sure to change your furnace filter and clean your A/C evaporator coils using mild detergents and water for maximum equipment efficiency."
			],
		'tip14': 
			[level:2, 
				text:"Your ecobee thermostat works with wireless remote sensors to measure temperature and occupancy in multiple rooms and make smarter heating and cooling decisions for you. They keep you comfortable while saving you money." +
				"Just as you can select which sensors participate in Comfort Settings, you can select which don’t participate." +                 
				"To configure your sensor participation settings, select Menu, Sensors, Sensor Name, Participation and select all of the Comfort Settings you want this sensor to participate in."			
			],
		'tip15': 
			[level:2, 
				text:"Setting your fan’s minimum runtime per hour at the thermostat unit in Main Menu, Settings, Installation Settings, and finally Thresholds  will help distribute the air around the home, creating more even temperature in the home. You’ll be more comfortable, and with more even temperatures the ecobee will not engage your HVAC as often to compensate for hot and cold spots. " +
				"You will be more comfortable at home and save energy while you’re at it. To change the MinimumFanOnTime setting, Select Main Menu, Quick Changes, Fan on your thermostat unit or mobile app. "+                
				"You can also set a MinimumFanOnTime value per ecobee schedule using the ecobeeFanMinOnTime or ecobeeSetZoneWithSchedule smartapps.  Contact ecomatiq homes.com for more details."
			],
		'tip16': 
			[level:2, 
				text:"You should change the default Sleep climate at ecobee according to your Sleep schedule and use energy efficient settings as indicated on the energy.gov website." +
				"You can save as much as 10% to 20% a year on heating and cooling by doing a temperature setback or setforward of 7 degrees Fahrenheit or 3 degrees Celsius for 8 hours a day from its normal setting." 
			], 
		'tip17': 
			[level:2, 
				text:"You should set the default Away climate at ecobee according to your work schedule and use energy efficient settings as indicated on the energy.gov website." +
				"You can save as much as 10% to 20% a year on heating and cooling by doing a temperature setback or setforward of 7 degrees Fahrenheit or 3 degrees Celsius for 8 hours a day from its normal setting." 
			],
		'tip18': 
			[level:2, 
				text:"If you're away for a long period of time, you should use the Vacation mode. The Vacation feature on your ecobee helps you conserve energy while you are away for extended periods and ensures your home is at a comfortable temperature before you arrive home. " +
				"The Vacation feature is smart, which means you can leave your normal schedule as-is, and your ecobee will automatically return to it when your vacation ends. You can even create multiple vacations, so you can program it right when you book your vacations, and not have to think about it again." 
			],
		'tip20': 
			[level:3, 
				text:"To maintain comfort inside the home, the humidity inside the home must be controlled along with the temperature of the air. Consider a warm summers day, if the humidity inside the home increases, the air will hold more heat " +
				"and the air conditioner will need to run longer to offset both the humidity and the warm air. The same principle applies for heating as it takes more time to heat air heavy with humidity."
			],
		'tip21': 
			[level:3, 
				text:"To maintain comfort inside the home, the humidity inside the home must be controlled along with the temperature of the air. As you don't have a dehumidifer connected to ecobee, you can use " +
					"the A/C to dehumidify your home by using the A/C Overcool option. In the thermostat menu, Select Settings, Installation Settings, Thresholds, and finally AC Overcool Max." +
					"The amount the system will cool your house beyond the desired temperature in the currently used Comfort Setting is determined by the desired temperature itself."                    
			],
		'tip22': 
			[level:3, 
				text:"To maintain comfort inside the home, the humidity inside the home must be controlled along with the temperature of the air. Your dehumidifier is connected to your ecobee, you should then use " +
					"ecomatiq homes specialized MonitorAndSetEcobeeHumidity smartapp to activate automatically your dehumidifier when required. Contact ecomatiq homes.com for more details."
			],
		'tip23': 
			[level:3, 
				text:"Your HRV or ERV is connected to your ecobee, you should then use " +
					"ecomatiq homes zoned heating and cooling smartapps to automatically activate your HRV/ERV for free cooling. Contact ecomatiq homes.com for more details."
			],
		'tip24': 
			[level:3, 
				text:"To maintain comfort inside the home, the humidity inside the home must be controlled along with the temperature of the air. Your HRV/ERV is connected to your ecobee, you should then use " +
					"ecomatiq homes specialized MonitorAndSetEcobeeHumidity smartapp to activate automatically your dehumidifier when required. Contact ecomatiq homes.com for more details."
			],
		'tip25': 
			[level:3, 
				text:"The auxMaxOutdoorTemp parameter is found in Installation Settings,Thresholds, and finally Aux Heat Settings. This is the temperature at which ecobee will not run auxiliary Heat under any circumstances. " +
				"This is an energy savings feature which is designed to allow people very concerned with savings to ensure the auxiliary Heat does not run if it is mild outside. " +
 				"This value should be set to a high value close to your comfort temperature." +
				"The auxOutdoorTemp setting should not be set at or below freezing temperature; otherwise, if you need auxHeat and the temperature is low, your pipes may freeze."
			],
		'tip26': 
			[level:3, 
				text:"Here are some concepts about staging: when set to auto for a given type of heating (i.e. heat pump or resistive strips), ecobee calculates the time it will take to achieve setpoint (considering outside temperature" + 
				"and the way your house and equipment perform) and if that time is more than 10 minutes, ecobee will upstage right away. You can override this behavior using " +
				"the following ecobee settings at the thermostat unit: Compressor Stage 1 Max Runtime, Compressor to Aux Temp Delta, Compressor to Stage2 Temp Delta, and Compressor Reverse Staging." +
				"If reverse staging is enabled, the thermostat will cycle down from the higher stages so that as ecobee approaches the set point, it will only be running instage 1." +
				"In terms of seeing when stage 1 or 2 are running, if you have configured your auxHeat as two-stage, when you go to the ecobee web portal," + 
				"under the HomeIQ menu item, stage 1 and stage 2 auxHeat will be plotted separately on the graph. You can use the toggle buttons in the legend" + 
				"at the bottom of the graph to turn off or on different values so that you can look at specific values more easily." +
				"Please consult your HVAC manual for your best equipment settings. You can also contact ecobee support if needed."
			],
		'tip27': 
			[level:3, 
				text:"To maintain comfort inside the home, the humidity inside the home must be controlled along with the temperature of the air. Your humidifier is connected to your ecobee, you should then use " +
					"ecomatiq homes specialized MonitorAndSetEcobeeHumidity smartapp to activate automatically your humidifier when required. In Winter, you can take advantage of the frost control feature that you can set at the thermostat unit or in the smartapp." +
					"For more details about MonitorAndSetEcobeeHumidity, contact ecomatiq homes.com."                    
			],
		'tip30': 
			[level:3, 
				text:"Check if your thermostat has the smart recovery feature enabled at the unit in Main Menu, Settings, Preferences. As you use your ecobee, it learns how long it takes for your home to " +
				"reach your desired temperature, taking into account the performance of your equipment and how the weather effects the regulation of the indoor temperature."                
			],
		'tip31': 
			[level:3, 
				text:"The outdoor temperature has been fairly constant, yet your A/C or Furnace runtime has increased in the last day." +
				"Try to use the ecomatiq homes windowOrDoorOpen smartapp to avoid cooling or heating your home when a door or window contact is open for too long." +
				"For more comfort, you should also try to heat or cool your home using the zoned heating and cooling smartapps available at www.ecomatiq homes.com."
		],
		'tip32': 
			[level:3, 
				text:"To reduce the number of cooling cycles and cooling runtime, you should try " +
					"ecomatiq homes zoned heating and cooling smartapps to activate automatically a damper or big fan or evaporative cooler switch to enable free cooling." +
					"Contact ecomatiq homes.com for more details."
			],
		'tip40': 
			[level:4, 
				text:"Over time, you may want to keep track your HVAC efficiency and this smartapp can give you some metrics about it.  Consult your manual and make sure that your HVAC " +
				"can reach its balance point. Your HVAC efficiency changes with outdoor temperature. The balance point is the temperature at which it is more efficient to run Auxiliary Heat instead of your main HVAC component."
			],
		'tip41': 
			[level:4, 
				text:"Over time, you may want to keep track your HVAC efficiency and this smartapp can give you some metrics about it.  Consult your manual and make sure that your HVAC " +
				"can reach its balance point. Your HVAC efficiency changes with outdoor temperature. The balance point is the temperature at which it is more efficient to run Auxiliary Heat instead of your main HVAC component."
			],
		'tip42': 
			[level:4, 
				text:"Over time, you may want to keep track your HVAC efficiency and this smartapp can give you some metrics about it.  Consult your manual and make sure that your HVAC " +
				"can reach its balance point. Your HVAC efficiency changes with outdoor temperature. The balance point is the temperature at which it is more efficient to run Auxiliary Heat instead of your main HVAC component."
			],
		'tip43': 
			[level:4, 
				text:"Over time, you may want to keep track your HVAC efficiency and this smartapp can give you some metrics about it.  Consult your manual and make sure that your HVAC " +
				"can reach its balance point. Your HVAC efficiency changes with outdoor temperature. The balance point is the temperature at which it is more efficient to run Auxiliary Heat instead of your main HVAC component."
			],
		'tip44': 
			[level:4, 
				text:"Over time, you may want to keep track your HVAC efficiency and this smartapp can give you some metrics about it.  Consult your manual and make sure that your HVAC " +
				"can reach its balance point. Your HVAC efficiency changes with outdoor temperature"
			],
		'tip45': 
			[level:4, 
				text:"Over time, you may want to keep track your HVAC efficiency and this smartapp can give you some metrics about it.  Consult your manual and make sure that your HVAC " +
				"can reach its balance point. Your HVAC efficiency changes with outdoor temperature."
			],
		'tip46': 
			[level:4, 
				text:"Over time, you may want to keep track your HVAC efficiency and this smartapp can give you some metrics about it.  Consult your manual and make sure that your HVAC " +
				"can reach its balance point. Your HVAC efficiency changes with outdoor temperature."
			],
		'tip47': 
			[level:4, 
				text:"Over time, you may want to keep track your HVAC efficiency and this smartapp can give you some metrics about it.  Consult your manual and make sure that your HVAC " +
				"can reach its balance point. Your HVAC efficiency changes with outdoor temperature."
			]
	]
	def recommendation = tips.getAt(tip)
	traceEvent(settings.logFilter,"get_recommendation_text>got ${tip}'s text from tips table", settings.trace)
	return recommendation
}

private void initialize_tips() {
	for (int i=1;i<=get_MAX_TIPS();i++) {    
		def attribute = "tip${i}Text"    
		sendEvent name: attribute, value: "",displayed: (settings.trace?:false)
		attribute = "tip${i}Level"
		sendEvent name: attribute, value: "",displayed: (settings.trace?:false)
		attribute = "tip${i}Solution"
		sendEvent name: attribute, value: "",displayed: (settings.trace?:false)
	}        

}

private boolean isWeekday() {
	Calendar myDate = Calendar.getInstance()
	int dow = myDate.get (Calendar.DAY_OF_WEEK)
	boolean isWeekday = ((dow >= Calendar.MONDAY) && (dow <= Calendar.FRIDAY))
	return isWeekday
}

void resetTips() {
	traceEvent(settings.logFilter,"resetTips>begin with state?.tips=${state.tips}, about to reset the state variable",settings.trace)
	state?.tips=[] // reset the state variable which saves all tips previously given
}

void getTips(level=1) {
	if (state?.tips==null) {resetTips()}
	state?.currentTip=0
    
	def scale = state?.scale
	def tipsAlreadyGiven = (state?.tips != [])? state.tips: []
	def maxTips=get_MAX_TIPS()    
	traceEvent(settings.logFilter,"getTips>begin with level=$level",settings.trace)
	traceEvent(settings.logFilter,"getTips>tipsAlreadyGiven=$tipsAlreadyGiven,state.tips=${state?.tips}",settings.trace)
	initialize_tips()   
	def recommendation=null
	def attribute    
	boolean isWeekday=isWeekday()    
    
	float MAX_DEVIATION_TEMPERATURE= (scale=='F')?0.6:0.3
	float MAX_DIFFERENCE_TEMPERATURE=(scale=='F')?3:1.5
	float MAX_DEVIATION_OUTDOOR_TEMP=(scale=='F')?10:5
	float MIN_DEVIATION_COOLING_SETPOINT= (scale=='F')?6:3
	float MIN_DEVIATION_HEATING_SETPOINT= (scale=='F')?6:3
	int MAX_HUMIDITY_LEVEL=55
	int HUMIDITY_DIFF_ALLOWED=3
	int MILLISECONDS_PER_HOUR=(60 * 60 * 1000)    
	int MAX_HOLD_THRESHOLD=3
	int MAX_HEATING_CYCLE=10
	int MAX_COOLING_CYCLE=10
    
	def countHeating=0, countCooling=0,countSleep=0,countAway=0,countHome=0,sleepDuration=0,awayDuration=0,countHolds=0
	def modelNumber=getModelNumber()
	def component

	String mode = device.currentValue("thermostatMode")    
	Date endDate= new Date()
	Date startDate = endDate -1
	Date aWeekAgo=endDate-7
	if ((level == 1) ||  (level == 0)) {
    
		def minCoolingSetpoint,maxCoolingSetpoint
		def minHeatingSetpoint,maxHeatingSetpoint
		def coolingValuesSet=get_stats_attribute("coolingSetpoint",startDate,endDate,'values')
		def heatingValuesSet=get_stats_attribute("heatingSetpoint",startDate,endDate,'values')
    
		if (mode in ['cool','auto', 'off']) {    
			countCooling=get_stats_attribute("thermostatOperatingState",startDate,endDate,'count',false,'cooling')
			minCoolingSetpoint=coolingValuesSet.min()    
			maxCoolingSetpoint=coolingValuesSet.max()    
		}        
		if (mode in ['heat','auto', 'off']) {
			countHeating=get_stats_attribute("thermostatOperatingState",startDate,endDate,'count',false,'heating')
			minHeatingSetpoint=heatingValuesSet.min()    
			maxHeatingSetpoint=heatingValuesSet.max()    
		}    
		countHolds=get_stats_attribute("programScheduleName",startDate,endDate,'count',false,'hold')  
		countAway=get_stats_attribute("climateName",startDate,endDate,'count',false,'Away')  
		countSleep=get_stats_attribute("setClimate",startDate,endDate,'count',false,'Sleep')  
		countHome=get_stats_attribute("setClimate",startDate,endDate,'count',false,'Home')  
		def outdoorTemp = device.currentValue("weatherTemperature")
		int currentIndoorHum = device.currentValue("humidity")            
		def currentTemp = device.currentValue("temperature")            
    
		if (settings.trace) {    
			traceEvent(settings.logFilter,"getTips>currentTemp = $currentTemp", settings.trace)
			traceEvent(settings.logFilter,"getTips>currentIndoorHum = $currentIndoorHum", settings.trace)            
			traceEvent(settings.logFilter,"getTips>outdoorTemp=$outdoorTemp", settings.trace)
			traceEvent(settings.logFilter,"getTips>countCooling=$countCooling", settings.trace)
			traceEvent(settings.logFilter,"getTips>countHeating=$countHeating", settings.trace)
			traceEvent(settings.logFilter,"getTips>countHolds=$countHolds", settings.trace)
			traceEvent(settings.logFilter,"get_tips>coolingSetPoint values=$coolingValuesSet", settings.trace)
			traceEvent(settings.logFilter,"get_tips>heatingSetPoint values=$heatingValuesSet", settings.trace)
			traceEvent(settings.logFilter,"getTips>min coolingSetpoint=$minCoolingSetpoint", settings.trace)
			traceEvent(settings.logFilter,"getTips>min heatingSetpoint=$minHeatingSetpoint", settings.trace)
			traceEvent(settings.logFilter,"getTips>max coolingSetpoint=$maxCoolingSetpoint", settings.trace)
			traceEvent(settings.logFilter, "getTips>max heatingSetpoint=$maxHeatingSetpoint", settings.trace)
			traceEvent(settings.logFilter,"getTips>countAway=$countAway", settings.trace)
			traceEvent(settings.logFilter,"getTips>countSleep=$countSleep", settings.trace)
		}   
		if (!tipsAlreadyGiven.contains("tip01")) {
			if (countHeating && !countSleep) {
				recommendation= get_recommendation('tip01')
				tipsAlreadyGiven.add('tip01')                
				state?.currentTip=state?.currentTip +1             
				attribute ="tip${state?.currentTip}Text"                
				sendEvent name: attribute, value: "Observation: Your thermostat's minimum heating setpoint of ${minHeatingSetpoint} degrees could be decreased at Nights. Current tip is: " + recommendation.text
					displayed: (settings.trace?:false)
				attribute ="tip${state?.currentTip}Level"                
				sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
				attribute ="tip${state?.currentTip}Solution"                
				sendEvent name: attribute, value: "tip01,heating",displayed: (settings.trace?:false)
			}        
			if ((!tipsAlreadyGiven.contains("tip01")) && countCooling && !countSleep) {
				recommendation= get_recommendation('tip01')
				tipsAlreadyGiven.add('tip01')                
				state?.currentTip=state?.currentTip +1
				attribute ="tip${state?.currentTip}Text"                
				sendEvent name: attribute, value: "Observation: Your thermostat's maximum cooling setpoint of ${maxCoolingSetpoint} degrees could be increased at Nights. Current tip is: " + recommendation.text,
					displayed: (settings.trace?:false)
				attribute ="tip${state?.currentTip}Level"                
				sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
				attribute ="tip${state?.currentTip}Solution"                
				sendEvent name: attribute, value: "tip01,cooling",displayed: (settings.trace?:false)
			}        
		} /* end of tip01 logic */
		if ((isWeekday) && (!tipsAlreadyGiven.contains("tip02"))) {
			if ((mode in ['heat','auto', 'off']) && countHeating && !countAway) {
				recommendation= get_recommendation('tip02')
				tipsAlreadyGiven.add('tip02')                
				state?.currentTip=state?.currentTip +1             
				attribute ="tip${state?.currentTip}Text"                
				sendEvent name: attribute, value: "Observation: Your thermostat's minimum heating setpoint of ${minHeatingSetpoint} degrees could be decreased during workdays when away for work. Current tip is: " + recommendation.text,
					displayed: (settings.trace?:false)
				attribute ="tip${state?.currentTip}Level"                
				sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
				attribute ="tip${state?.currentTip}Solution"                
				sendEvent name: attribute, value: "tip02,heating",displayed: (settings.trace?:false)
			}        
			if (((!tipsAlreadyGiven.contains("tip02")) && countCooling) && !countAway) {
				recommendation= get_recommendation('tip02')
				tipsAlreadyGiven.add('tip02')                
				state?.currentTip=state?.currentTip +1             
				attribute ="tip${state?.currentTip}Text"                
				sendEvent name: attribute, value: "Observation: Your thermostat's maximum cooling setpoint of ${maxCoolingSetpoint} degrees could be increased during workdays when away for work. Current tip is: " + recommendation.text,
					displayed: (settings.trace?:false)
				attribute ="tip${state?.currentTip}Level"                
				sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
				attribute ="tip${state?.currentTip}Solution"                
				sendEvent name: attribute, value: "tip02,cooling",displayed: (settings.trace?:false)
			}        
		} /* end of tip02 logic */
		if ((state?.currentTip < maxTips) && (data.thermostatCount > 1) && (!tipsAlreadyGiven.contains("tip03"))) {
			recommendation= get_recommendation('tip03')
			tipsAlreadyGiven.add('tip03')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			sendEvent name: attribute, value: "Observation: It seems that you have ${data.thermostatCount} ecobee thermostats at your location." + recommendation.text,
				displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip03",displayed: (settings.trace?:false)
		}	  
           
		if ((state?.currentTip < maxTips) && !tipsAlreadyGiven.contains("tip04") ) {
			if ((countHeating) && (countHeating.toInteger() >= MAX_HEATING_CYCLE)) {
				recommendation= get_recommendation('tip04')
				tipsAlreadyGiven.add('tip04')                
				state?.currentTip=state?.currentTip +1             
				attribute ="tip${state?.currentTip}Text"                
				sendEvent name: attribute, value: "Observation: There were ${countHeating} heating cycles in the last 24 hours. Current tip is: " + recommendation.text,
					displayed: (settings.trace?:false)
				attribute ="tip${state?.currentTip}Level"                
				sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
				attribute ="tip${state?.currentTip}Solution"                
				sendEvent name: attribute, value: "tip04,heating",displayed: (settings.trace?:false)
			} 
			if ((state?.currentTip < maxTips) && ((countCooling) && (countCooling.toInteger() >= MAX_COOLING_CYCLE))) {
				recommendation= get_recommendation('tip04')
				tipsAlreadyGiven.add('tip04')                
				state?.currentTip=state?.currentTip +1             
				attribute ="tip${state?.currentTip}Text"                
				sendEvent name: attribute, value: "Observation: There were ${countCooling} cooling cycles in the last 24 hours. Current tip is: " + recommendation.text,
					displayed: (settings.trace?:false)
				attribute ="tip${state?.currentTip}Level"                
				sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
				attribute ="tip${state?.currentTip}Solution"                
				sendEvent name: attribute, value: "tip04,cooling",displayed: (settings.trace?:false)
			}            
        
		} /* end of tip11 logic */
		if ((state?.currentTip < maxTips) && (countHolds >= MAX_HOLD_THRESHOLD) && (!tipsAlreadyGiven.contains("tip05"))) {
			recommendation= get_recommendation('tip05')
			tipsAlreadyGiven.add('tip05')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			sendEvent name: attribute, value: "Observation: Your thermostat has been on hold ${countHolds} times during the day.  Current tip is: " + recommendation.text,
				displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip05",displayed: (settings.trace?:false)
		} /* end of tip05 logic */
		if ((state?.currentTip < maxTips) && (countHolds >= MAX_HOLD_THRESHOLD) && (!tipsAlreadyGiven.contains("tip06"))) {
			recommendation= get_recommendation('tip06')
			tipsAlreadyGiven.add('tip06')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			sendEvent name: attribute, value: "Observation: Your thermostat has been on hold ${countHolds} times during the day.  Current tip is: " + recommendation.text,
				displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip06",displayed: (settings.trace?:false)
		} /* end of tip06 logic */
		state?.tips=tipsAlreadyGiven    
		traceEvent(settings.logFilter,"getTips>end Level 1 with tipsAlreadyGiven=$tipsAlreadyGiven,state.tips=${state?.tips}",settings.trace)
	} /* end of level 1 */    
	    

	if ((state?.currentTip < maxTips) && ((level == 2) ||  (level == 0))) {
		tipsAlreadyGiven=get_tips_level2()    
		state?.tips = tipsAlreadyGiven 
	}  
    
	if ((state?.currentTip < maxTips) && ((level == 3) ||  (level == 0))) {
		tipsAlreadyGiven=get_tips_level3()    
		state?.tips = tipsAlreadyGiven 
	}  
    
	if ((state?.currentTip < maxTips)  && ((level ==4) || (level ==0))) {
		tipsAlreadyGiven=get_tips_level4()    
		state?.tips = tipsAlreadyGiven 
	}    
	traceEvent(settings.logFilter,"getTips>end with tipsAlreadyGiven=$tipsAlreadyGiven,state.tips=${state?.tips}",settings.trace)

}

private def get_tips_level2() {
	def tipsAlreadyGiven = (state?.tips != [])? state.tips: []
	traceEvent(settings.logFilter,"get_tips_level2>begin with tipsAlreadyGiven=$tipsAlreadyGiven,state.tips=${state?.tips}",settings.trace)
	def scale = state?.scale
	def maxTips=get_MAX_TIPS()    
	def countHeating=0, countCooling=0,sleepDuration=0,awayDuration=0,countHolds=0
	def component

	def recommendation=null
	def attribute    
	boolean isWeekday=isWeekday()    
    
	float MAX_DEVIATION_TEMPERATURE= (scale=='F')?0.6:0.3
	float MAX_DIFFERENCE_TEMPERATURE=(scale=='F')?3:1.5
	int MAX_HEATING_CYCLE=10
	int MAX_COOLING_CYCLE=10
	int MAX_COOLING_MINUTES_TIME=600   
	int MAX_HEATING_MINUTES_TIME=300    
	float MAX_DEVIATION_OUTDOOR_TEMP=(scale=='F')?10:5
	int MILLISECONDS_PER_HOUR=(60 * 60 * 1000)    
	int MIN_USUAL_SLEEP_DURATION=7
	int MIN_USUAL_AWAY_DURATION=4 
	int MAX_USUAL_AWAY_DURATION=20
	float MINIMUM_FAN_RUNTIME=400    
	Date endDate= new Date()
	Date startDate = endDate -1
	Date aWeekAgo=endDate-7
    

	String mode = device.currentValue("thermostatMode")    
	int currentIndoorHum = device.currentValue("humidity")            
	def currentTemp = device.currentValue("temperature")            
	def outdoorTemp = device.currentValue("weatherTemperature")
	def minCoolingSetpoint,maxCoolingSetpoint
	def minHeatingSetpoint,maxHeatingSetpoint
	def coolingValuesSet=get_stats_attribute("coolingSetpoint",startDate,endDate,'values')
	def heatingValuesSet=get_stats_attribute("heatingSetpoint",startDate,endDate,'values')
    
	if (mode in ['cool','auto', 'off']) {    
		countCooling=get_stats_attribute("thermostatOperatingState",startDate,endDate,'count',false,'cooling')
		minCoolingSetpoint=coolingValuesSet.min()    
		maxCoolingSetpoint=coolingValuesSet.max()    
	}        
	if (mode in ['heat','auto', 'off']) {
		countHeating=get_stats_attribute("thermostatOperatingState",startDate,endDate,'count',false,'heating')
		minHeatingSetpoint=heatingValuesSet.min()    
		maxHeatingSetpoint=heatingValuesSet.max()    
	}    
	def awayDates=get_stats_attribute("setClimate",startDate,endDate,'dates',false,'Away')
	def homeDates=get_stats_attribute("setClimate",startDate,endDate,'dates',false,'Home')
	def sleepDates=get_stats_attribute("setClimate",startDate,endDate,'dates',false,'Sleep')
	def countAway=get_stats_attribute("climateName",startDate,endDate,'count',false,'Away')  
	def countSleep=get_stats_attribute("setClimate",startDate,endDate,'count',false,'Sleep')  
	def countHome=get_stats_attribute("setClimate",startDate,endDate,'count',false,'Home')  
	if (countHome && countSleep) {    
		def homeMaxDate=homeDates.max()
		def sleepMaxDate=sleepDates.max()
		sleepDuration=(((homeMaxDate.toLong() - sleepMaxDate.toLong() ).abs()) / MILLISECONDS_PER_HOUR).toFloat().round(1)    
	}
	if (countHome && countAway) {    
		def homeMaxDate=homeDates.max()
		def awayMaxDate=awayDates.max()
		awayDuration=(((homeMaxDate.toLong() - awayMaxDate.toLong()).abs()) / MILLISECONDS_PER_HOUR).toFloat().round(1)    
	}
	def remoteMinTemp = get_stats_attribute("remoteSensorMinTemp",startDate,endDate,'min')     
	def remoteMaxTemp = get_stats_attribute("remoteSensorMaxTemp",startDate,endDate,'max')     
	def remoteAvgTemp = get_stats_attribute("remoteSensorAvgTemp",startDate,endDate,'avg')
	def avgTemperature=get_stats_attribute("temperature",startDate,endDate,'avg')
	def devTemperature=get_stats_attribute("temperature",startDate,endDate,'deviation')
    
	if (settings.trace) {    
		traceEvent(settings.logFilter,"get_tips_level2>currentTemp = $currentTemp", settings.trace)
		traceEvent(settings.logFilter, "get_tips_level2>avgTemperature=$avgTemperature", settings.trace)    
		traceEvent(settings.logFilter,"get_tips_level2>currentIndoorHum = $currentIndoorHum", settings.trace)           
		traceEvent(settings.logFilter,"get_tips_level2>outdoorTemp=$outdoorTemp", settings.trace)
		traceEvent(settings.logFilter, "get_tips_level2>countCooling=$countCooling", settings.trace)
		traceEvent(settings.logFilter, "get_tips_level2>countHeating=$countHeating", settings.trace)
		traceEvent(settings.logFilter, "get_tips_level2>coolingSetPoint values=$coolingValuesSet", settings.trace)
		traceEvent(settings.logFilter, "get_tips_level2>heatingSetPoint values=$heatingValuesSet", settings.trace)
		traceEvent(settings.logFilter, "get_tips_level2>min heatingSetpoint=$minHeatingSetpoint", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level2>min coolingSetpoint=$minCoolingSetpoint", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level2>max coolingSetpoint=$maxCoolingSetpoint", settings.trace)
		traceEvent(settings.logFilter, "get_tips_level2>max heatingSetpoint=$maxHeatingSetpoint", settings.trace)
		traceEvent(settings.logFilter, "get_tips_level2>awayDates=$awayDates", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level2>sleepDates=$sleepDates", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level2>sleepDuration=$sleepDuration", settings.trace)
		traceEvent(settings.logFilter, "get_tips_level2>awayDuration=$awayDuration", settings.trace)
		traceEvent(settings.logFilter, "get_tips_level2>homeDates=$homeDates", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level2>remoteMinTemp=$remoteMinTemp", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level2>remoteMaxTemp=$remoteMaxTemp", settings.trace)        	
		traceEvent(settings.logFilter,"get_tips_level2>remoteAvgTemp=$remoteAvgTemp" , settings.trace)
	}   
	if ((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip10")) && (countHeating && maxHeatingSetpoint && minHeatingSetpoint) && ((maxHeatingSetpoint.toFloat() -minHeatingSetpoint.toFloat()) <= MIN_DEVIATION_HEATING_SETPOINT)) {
		recommendation= get_recommendation('tip10')
		tipsAlreadyGiven.add('tip10')                
		state?.currentTip=state?.currentTip +1             
		attribute ="tip${state?.currentTip}Text"                
		sendEvent name: attribute, value: "Observation: There is a not a big difference between your thermostat's minimum heating setpoint of ${minHeatingSetpoint} and your maximum heating setpoint of ${maxHeatingSetpoint} degrees in the last 24 hours. Current tip is: " + recommendation.text,
			displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Level"                
		sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Solution"                
		sendEvent name: attribute, value: "tip10,heating",displayed: (settings.trace?:false)
	}        
	if ((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip10")) && (countCooling && maxCoolingSetpoint && minCoolingSetpoint) && ((maxCoolingSetpoint.toFloat() -minCoolingSetpoint.toFloat()) <= MIN_DEVIATION_COOLING_SETPOINT)) {
		recommendation= get_recommendation('tip10')
		tipsAlreadyGiven.add('tip10')                
		state?.currentTip=state?.currentTip +1             
		attribute ="tip${state?.currentTip}Text"                
		sendEvent name: attribute, value: "Observation: There is a not a big difference between your thermostat's maximum cooling setpoint of ${maxCoolingSetpoint} and your minimum cooling setpoint of ${minCoolingSetpoint} degrees in the last 24 hours. Current tip is: " + recommendation.text,
		displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Level"                
		sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Solution"                
		sendEvent name: attribute, value: "tip10,cooling",displayed: (settings.trace?:false)
	} /* end of tip10 logic */
        

	if ((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip13")) && (devTemperature.toFloat() >=MAX_DEVIATION_TEMPERATURE)) {
		recommendation= get_recommendation('tip13')
		tipsAlreadyGiven.add('tip13')                 
		state?.currentTip=state?.currentTip +1             
		attribute ="tip${state?.currentTip}Text"                
		sendEvent name: attribute, value: "Observation: Your average indoor temperature of ${avgTemperature} degrees has not been constant in the last 24 hours as the standard deviation is ${devTemperature} degrees. Current tip is: " + recommendation.text,
			displayed: (settings.trace?:false)                
		attribute ="tip${state?.currentTip}Level"                
		sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
	}	
	if ((state?.currentTip < maxTips) && (remoteMinTemp && remoteAvgTemp)) {
		if ((((remoteMinTemp.toFloat() - remoteAvgTemp.toFloat()).abs()) > MAX_DIFFERENCE_TEMPERATURE) ||
			(((remoteMaxTemp.toFloat() - remoteAvgTemp.toFloat()).abs()) > MAX_DIFFERENCE_TEMPERATURE)) {
			recommendation= get_recommendation('tip13')
			float delta_temp_max = (remoteMaxTemp.toFloat() - remoteAvgTemp.toFloat()).abs().round(1)                
			float delta_temp_min = (remoteMinTemp.toFloat() - remoteAvgTemp.toFloat()).abs().round(1)                
			tipsAlreadyGiven.add('tip13')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			sendEvent name: attribute, value: "Observation: Your average remote sensors' temperature is ${remoteAvgTemp} degrees, but their temperature varies quite a lot (+${delta_temp_max}/-${delta_temp_min} degrees) from room to room. Current tip is: " + recommendation.text,
				displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip13",displayed: (settings.trace?:false)
		} else if ((!tipsAlreadyGiven.contains("tip14"))) {
			recommendation= get_recommendation('tip14')
			tipsAlreadyGiven.add('tip14')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			sendEvent name: attribute, value: "Observation: The temperature at the thermostat varies during the day as the standard deviation is ${devTemperature} degrees. Current tip is: " + recommendation.text,
				displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip14",displayed: (settings.trace?:false)
		} /* end tip 14 */
	}        
	if ((state?.currentTip < maxTips) && ((fanRuntime) && (fanRuntime < MINIMUM_FAN_RUNTIME)) &&  (!tipsAlreadyGiven.contains("tip15")) && (remoteMinTemp && remoteAvgTemp))  {
		float delta_temp_max = (remoteMaxTemp.toFloat() - remoteAvgTemp.toFloat()).abs().round(1)                
		float delta_temp_min = (remoteMinTemp.toFloat() - remoteAvgTemp.toFloat()).abs().round(1)                
		if ((delta_temp_max > MAX_DIFFERENCE_TEMPERATURE) ||
			(delta_temp_min > MAX_DIFFERENCE_TEMPERATURE)) {
			recommendation= get_recommendation('tip15')
			tipsAlreadyGiven.add('tip15')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			sendEvent name: attribute, value: "Observation: Your Fan Runtime was ${fanRuntime} minutes yesterday, and you have some wide temperature deltas (+${delta_temp_max}/-${delta_temp_min} degrees) vs. the average of ${remoteAvgTemp} degrees " + 
				"between all your remote sensors. Current tip is: " + recommendation.text,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip15",displayed: (settings.trace?:false)
		}  		  
	}  		  
     
	if ((state?.currentTip < maxTips) &&  (!tipsAlreadyGiven.contains("tip16")) && (sleepDuration) && (sleepDuration.toFloat() < MIN_USUAL_SLEEP_DURATION)) {
		recommendation= get_recommendation('tip16')
		tipsAlreadyGiven.add('tip16')                
		state?.currentTip=state?.currentTip +1             
		attribute ="tip${state?.currentTip}Text"                
		sendEvent name: attribute, value: "Observation: Your Sleep program at ecobee was used for approximately ${sleepDuration} hour(s) " + 
			"which is below the usual sleep schedule of ${MIN_USUAL_SLEEP_DURATION} hours. Current tip is: " + recommendation.text,
			displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Level"                
		sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Solution"                
		sendEvent name: attribute, value: "tip16",displayed: (settings.trace?:false)
    
	}  	/* end of tip16 logic */  	  
	if ((isWeekday) && (state?.currentTip < maxTips) &&  (!tipsAlreadyGiven.contains("tip17")) && (awayDuration) && (awayDuration.toFloat() < MIN_USUAL_AWAY_DURATION)) {
		recommendation= get_recommendation('tip17')
		tipsAlreadyGiven.add('tip17')                
		state?.currentTip=state?.currentTip +1             
		attribute ="tip${state?.currentTip}Text"                
		sendEvent name: attribute, value: "Observation: Your Away program at ecobee was used for approximately ${awayDuration} hour(s) in the last day " + 
			"which is below the usual Away schedule of ${MIN_USUAL_AWAY_DURATION} hours if you work 9-5. Current tip is: " + recommendation.text,
			displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Level"                
		sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Solution"                
		sendEvent name: attribute, value: "tip17",displayed: (settings.trace?:false)
	}  	/* end of tip17 logic */  	
        
	if ((state?.currentTip < maxTips) &&  (!tipsAlreadyGiven.contains("tip18")) && (awayDuration) && (awayDuration.toFloat() > MAX_USUAL_AWAY_DURATION)) {
		recommendation= get_recommendation('tip18')
		tipsAlreadyGiven.add('tip18')                
		state?.currentTip=state?.currentTip +1             
		attribute ="tip${state?.currentTip}Text"                
		sendEvent name: attribute, value: "Observation: Your Away program at ecobee was used for approximately ${awayDuration} hour(s) in the last day " + 
			"which is above the usual Away schedule of ${MAX_USUAL_AWAY_DURATION} hours if you work 9-5. Current tip is: " + recommendation.text,
			displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Level"                
		sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Solution"                
		sendEvent name: attribute, value: "tip18",displayed: (settings.trace?:false)
	}  	/* end of tip18 logic */  	
        
	state?.tips=tipsAlreadyGiven    
	traceEvent(settings.logFilter,"getTips>end Level 2 with tipsAlreadyGiven=$tipsAlreadyGiven,state.tips=${state?.tips}",settings.trace) 	     
	return tipsAlreadyGiven    

}   /* end getLevel 2 */  
 

private def get_tips_level3() {
	def tipsAlreadyGiven = (state?.tips != [])? state.tips: []
	traceEvent(settings.logFilter,"get_tips_level3>begin with tipsAlreadyGiven=$tipsAlreadyGiven,state.tips=${state?.tips}",settings.trace)
	def scale = state?.scale
	def maxTips=get_MAX_TIPS()    
	def recommendation=null
	def attribute,component    
	boolean isWeekday=isWeekday()    
    
	float MAX_DEVIATION_TEMPERATURE= (scale=='F')?0.6:0.3
	float MAX_DIFFERENCE_TEMPERATURE=(scale=='F')?3:1.5
	float MIN_DEVIATION_COOLING_SETPOINT= (scale=='F')?6:3
	float MIN_DEVIATION_HEATING_SETPOINT= (scale=='F')?6:3
	int MAX_HEATING_CYCLE=10
	int MAX_COOLING_CYCLE=10
	int MAX_COOLING_MINUTES_TIME=400    
	int MAX_HEATING_MINUTES_TIME=300    
	float MAX_DEVIATION_OUTDOOR_TEMP=(scale=='F')?10:5
	int MAX_HUMIDITY_LEVEL=55
	int HUMIDITY_DIFF_ALLOWED=3
	int MILLISECONDS_PER_HOUR=(60 * 60 * 1000)    
    
	Date endDate= new Date()
	Date startDate = endDate -1
	Date aWeekAgo=endDate-7
    
	String mode = device.currentValue("thermostatMode")    
	int currentIndoorHum = device.currentValue("humidity")            
	def currentTemp = device.currentValue("temperature")            
	def outdoorTemp = device.currentValue("weatherTemperature")
	def devOutdoorTemperature=get_stats_attribute("weatherTemperature",aWeekAgo,endDate,'deviation')
	def avgOutdoorTemperature=get_stats_attribute("weatherTemperature",aWeekAgo,endDate,'avg')
	int targetHumidity = find_ideal_indoor_humidity(outdoorTemp)
	int heatStages = device.currentValue("heatStages")?.toInteger()
	int coolStages = device.currentValue("coolStages")?.toInteger()
	String supportedThermostatModes=getSupportedThermostatModes()
	if ((heatStages==0) && (supportedThermostatModes.contains("auxHeatOnly"))) heatStages=1
    
 	def stage1HeatingDifferentialTemp= device.currentValue("stage1HeatingDifferentialTemp")
	def stage1HeatingDissipationTime= device.currentValue("stage1HeatingDissipationTime")
	def auxMaxOutdoorTemp=device.currentValue("auxMaxOutdoorTemp")

	def dailyHeatingRuntimeAux1 = device.currentValue("auxHeat1RuntimeDaily")?.toFloat()   
	def dailyHeatingRuntimeComp1= device.currentValue("compHeat1RuntimeDaily")?.toFloat()
	def yesterdayHeatingRuntimeAux1 = device.currentValue("auxHeat1RuntimeYesterday")?.toFloat()
	def yesterdayHeatingRuntimeComp1=device.currentValue("compHeat1RuntimeYesterday")?.toFloat()
	def avgWeeklyHeatingRuntimeAux1 = device.currentValue("auxHeat1RuntimeAvgWeekly")?.toFloat()
	def avgWeeklyHeatingRuntimeComp1= device.currentValue("compHeat1RuntimeAvgWeekly")?.toFloat()    
	def avgMonthlyHeatingRuntimeAux1 = device.currentValue("auxHeat1RuntimeAvgMonthly")?.toFloat()
	def avgMonthlyHeatingRuntimeComp1= device.currentValue("compHeat1RuntimeAvgMonthly")?.toFloat()   
	def dailyHeatingRuntimeAux2 = device.currentValue("auxHeat2RuntimeDaily")?.toFloat()
	def dailyHeatingRuntimeComp2=device.currentValue("compHeat2RuntimeDaily")?.toFloat()  
	def yesterdayHeatingRuntimeAux2 = device.currentValue("auxHeat2RuntimeYesterday")?.toFloat()
	def yesterdayHeatingRuntimeComp2=device.currentValue("compHeat2RuntimeYesterday")?.toFloat()
	def avgWeeklyHeatingRuntimeAux2  = device.currentValue("auxHeat2RuntimeAvgWeekly")?.toFloat()
	def avgWeeklyHeatingRuntimeComp2= device.currentValue("compHeat2RuntimeAvgWeekly")?.toFloat()    
	def avgMonthlyHeatingRuntimeAux2  = device.currentValue("auxHeat2RuntimeAvgMonthly")?.toFloat()
	def avgMonthlyHeatingRuntimeComp2= device.currentValue("compHeat2RuntimeAvgMonthly")?.toFloat()   
	def dailyHeatingRuntimeAux3  = device.currentValue("auxHeat3RuntimeDaily")?.toFloat()
	def dailyHeatingRuntimeComp3=device.currentValue("compHeat3RuntimeDaily")?.toFloat()  
	def yesterdayHeatingRuntimeAux3  = device.currentValue("auxHeat3RuntimeYesterday")?.toFloat()
	def yesterdayHeatingRuntimeComp3=device.currentValue("compHeat3RuntimeYesterday")?.toFloat()
	def avgWeeklyHeatingRuntimeAux3 = device.currentValue("auxHeat3RuntimeAvgWeekly")?.toFloat()
	def avgWeeklyHeatingRuntimeComp3= device.currentValue("compHeat3RuntimeAvgWeekly")?.toFloat()    
	def avgMonthlyHeatingRuntimeAux3 = device.currentValue("auxHeat3RuntimeAvgMonthly")?.toFloat()
	def avgMonthlyHeatingRuntimeComp3= device.currentValue("compHeat3RuntimeAvgMonthly")?.toFloat()   

	def dailyCoolingRuntimeCool1 = device.currentValue("compCool1RuntimeDaily")?.toFloat()
	def yesterdayCoolingRuntimeCool1 = device.currentValue("compCool1RuntimeYesterday")?.toFloat()
	def avgWeeklyCoolingRuntimeCool1 = device.currentValue("compCool1RuntimeAvgWeekly")?.toFloat()
	def avgMonthlyCoolingRuntimeCool1 = device.currentValue("compCool1RuntimeAvgMonthly")?.toFloat()
	def dailyCoolingRuntimeCool2 = device.currentValue("compCool2RuntimeDaily")?.toFloat()
	def yesterdayCoolingRuntimeCool2 = device.currentValue("compCool2RuntimeYesterday")?.toFloat()
	def avgWeeklyCoolingRuntimeCool2 = device.currentValue("compCool2RuntimeAvgWeekly")?.toFloat()
	def avgMonthlyCoolingRuntimeCool2 = device.currentValue("compCool2RuntimeAvgMonthly")?.toFloat()
	def fanRuntime = device.currentValue("fanRuntimeDaily")?.toFloat()
	def hasDehumidifier = (device.currentValue("hasDehumidifier")) ?: 'false'
	def hasHumidifier = (device.currentValue("hasHumidifier")) ?: 'false'
	def hasHrv = (device.currentValue("hasHrv")) ?: 'false'
	def hasErv = (device.currentValue("hasErv")) ?: 'false'
	float dailyCoolingRuntime =  (((dailyCoolingRuntimeCool1)?:0) + ((dailyCoolingRuntimeCool2)?:0)).toFloat().round(1)
	float yesterdayCoolingRuntime = (((yesterdayCoolingRuntimeCool1)?:0) + ((yesterdayCoolingRuntimeCool2)?:0)).toFloat().round(1)
	float avgWeeklyCoolingRuntime = (((avgWeeklyCoolingRuntimeCool1)?:0) + ((avgWeeklyCoolingRuntimeCool2)?:0)).toFloat().round(1)
	float avgMonthlyCoolingRuntime = (((avgMonthlyCoolingRuntimeCool1)?:0) + ((avgMonthlyCoolingRuntimeCool2)?:0)).toFloat().round(1)
	float dailyHeatingRuntime = (((dailyHeatingRuntimeComp1)?:0) + ((dailyHeatingRuntimeComp2)?:0) + ((dailyHeatingRuntimeComp3)?:0) + 
		(dailyHeatingRuntimeAux1)?:0) + ((dailyHeatingRuntimeAux2)?:0) + ((dailyHeatingRuntimeAux3)?:0).toFloat().round(1)
	float yesterdayHeatingRuntime = (((yesterdayHeatingRuntimeComp1)?:0) + ((yesterdayHeatingRuntimeComp2)?:0) + ((yesterdayHeatingRuntimeComp3)?:0) +
 		((yesterdayHeatingRuntimeAux1)?:0) + ((yesterdayHeatingRuntimeAux2)?:0) + ((yesterdayHeatingRuntimeAux3)?:0)).toFloat().round(1)
	float avgWeeklyHeatingRuntime = (((avgWeeklyHeatingRuntimeComp1)?:0) + ((avgWeeklyHeatingRuntimeComp2)?:0) + ((avgWeeklyHeatingRuntimeComp3)?:0) +
 		((avgWeeklyHeatingRuntimeAux1)?:0) + ((avgWeeklyHeatingRuntimeAux2)?:0) + ((avgWeeklyHeatingRuntimeAux3)?:0)).toFloat().round(1)
	float avgMonthlyHeatingRuntime = (((avgMonthlyHeatingRuntimeComp1)?:0) + ((avgMonthlyHeatingRuntimeComp2)?:0) + 
		((avgMonthlyHeatingRuntimeComp3)?:0) + ((avgMonthlyHeatingRuntimeAux1)?:0) + ((avgMonthlyHeatingRuntimeAux2)?:0) + 
		((avgMonthlyHeatingRuntimeAux3)?:0)).toFloat().round(1)
        
	if (settings.trace) {    
		traceEvent(settings.logFilter,"get_tips_level3>avgWeeklyCoolingRuntime=$avgWeeklyCoolingRuntime minutes", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level3>avgMonthlyHeatingRuntime=$avgMonthlyHeatingRuntime minutes", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level3>avgWeeklyHeatingRuntime=$avgWeeklyHeatingRuntime minutes", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level3>yesterdayCoolingRuntime=$yesterdayCoolingRuntime minutes", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level3>yesterdayHeatingRuntime=$yesterdayHeatingRuntime minutes", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level3>dailyCoolingRuntime=$dailyCoolingRuntime minutes", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level3>dailyHeatingRuntime=$dailyHeatingRuntime minutes", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level3>fanRuntime=$fanRuntime minutes", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level3>hasDehumidifier=$hasDehumidifier", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level3>hasHumidifier=$hasHumidifier", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level3>hasHrv=$hasHrv", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level3>hasErv=$hasErv", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level3>coolStages=$coolStages", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level3>heatStages=$heatStages", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level3>auxOutdoorTemp=$auxOutdoorTemp", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level3>stage1HeatingDifferentialTemp =$stage1HeatingDifferentialTemp", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level3>stage1HeatingDissipationTime =$stage1HeatingDissipationTime", settings.trace)
	}
	if ((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip20")) && (currentIndoorHum > (targetHumidity + HUMIDITY_DIFF_ALLOWED))) {
		recommendation= get_recommendation('tip20')
		tipsAlreadyGiven.add('tip20')                
		state?.currentTip=state?.currentTip +1             
		attribute ="tip${state?.currentTip}Text"                
		sendEvent name: attribute, value: "Observation: Your indoor humidity is currently ${currentIndoorHum}% " + 
			"which is well above your ideal indoor humidity of ${targetHumidity}% based on the current outdoor temperature of ${outdoorTemp} degrees. Current tip is: " + recommendation.text,
			displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Level"                
		sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Solution"                
		sendEvent name: attribute, value: "tip20",displayed: (settings.trace?:false)
 	}  		  
	if ((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip21")) && (hasdehumidifier !='true' && hasErv!='true' && hasHrv !='true') && (countCooling) && (currentIndoorHum > (targetHumidity + HUMIDITY_DIFF_ALLOWED))) { 
		recommendation= get_recommendation('tip21')
		tipsAlreadyGiven.add('tip21')                
		state?.currentTip=state?.currentTip +1            
		attribute ="tip${state?.currentTip}Text"                
		sendEvent name: attribute, value: "Observation: Your indoor humidity is currently ${currentIndoorHum}% " + 
				"which is well above your ideal indoor humidity of ${targetHumidity}% based on the current outdoor temperature of ${outdoorTemp} degrees. Current tip is: " + recommendation.text,
			displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Level"                
		sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Solution"                
		sendEvent name: attribute, value: "tip21",displayed: (settings.trace?:false)
	}
	if ((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip22")) && (hasDehumidifier =='true') && (currentIndoorHum > (targetHumidity + HUMIDITY_DIFF_ALLOWED))) {
		recommendation= get_recommendation('tip22')
		tipsAlreadyGiven.add('tip22')                
		state?.currentTip=state?.currentTip +1             
		attribute ="tip${state?.currentTip}Text"                
		sendEvent name: attribute, value: "Observation: Your indoor humidity is currently ${currentIndoorHum}% " + 
				"which is well above your ideal indoor humidity of ${targetHumidity}% based on the current outdoor temperature of ${outdoorTemp} degrees. Current tip is: " + recommendation.text
		attribute ="tip${state?.currentTip}Level"                
		sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Solution"                
		sendEvent name: attribute, value: "tip22",displayed: (settings.trace?:false)
    
	} else if ((state?.currentTip < maxTips) &&  (!tipsAlreadyGiven.contains("tip23")) && (hasErv=='true' || hasHrv =='true') && (countCooling) && (currentIndoorHum > (targetHumidity + HUMIDITY_DIFF_ALLOWED))) {
		recommendation= get_recommendation('tip23')
		tipsAlreadyGiven.add('tip23')                
		state?.currentTip=state?.currentTip +1             
		attribute ="tip${state?.currentTip}Text"                
		sendEvent name: attribute, value: "Observation: Your indoor humidity is currently ${currentIndoorHum}% " + 
				"which is well above your ideal indoor humidity of ${targetHumidity}% based on the current outdoor temperature of ${outdoorTemp} degrees. Current tip is: " + recommendation.text,
			displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Level"                
		sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Solution"                
		sendEvent name: attribute, value: "tip23",displayed: (settings.trace?:false)
	}    
	if ((state?.currentTip < maxTips) &&  (!tipsAlreadyGiven.contains("tip24")) && (hasErv=='true' || hasHrv =='true') && (currentIndoorHum > (targetHumidity + HUMIDITY_DIFF_ALLOWED))) {
		recommendation= get_recommendation('tip24')
		tipsAlreadyGiven.add('tip24')                
		state?.currentTip=state?.currentTip +1             
		attribute ="tip${state?.currentTip}Text"                
		sendEvent name: attribute, value: "Observation: Your indoor humidity is currently ${currentIndoorHum}% " + 
				"which is well above your ideal indoor humidity of ${targetHumidity}% based on the current outdoor temperature of ${outdoorTemp} degrees. Current tip is: " + recommendation.text,
			displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Level"                
		sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Solution"                
		sendEvent name: attribute, value: "tip24",displayed: (settings.trace?:false)
	}  	  /* end of tip24 logic */  
    
    
	if ((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip25")) && (auxMaxOutdoorTemp!=null) && (supportedThermostatModes.contains("auxHeatOnly"))) { 
		recommendation= get_recommendation('tip25')
		tipsAlreadyGiven.add('tip25')                
		state?.currentTip=state?.currentTip +1             
		attribute ="tip${state?.currentTip}Text"                
		sendEvent name: attribute, value: "Observation: You have a ${heatStages}-stage heating system and your auxMaxOutdoorTemp setting is ${auxMaxOutdoorTemp}." +  
			"Current tip is: " + recommendation.text,displayed: (settings.trace?:false)
             
		attribute ="tip${state?.currentTip}Level"                
		sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Solution"                
		sendEvent name: attribute, value: "tip25",displayed: (settings.trace?:false)

	} /* end of tip25 logic */  
	if ((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip26")) && (heatStages>1)) { 
		recommendation= get_recommendation('tip26')
		tipsAlreadyGiven.add('tip26')                
		state?.currentTip=state?.currentTip +1             
		attribute ="tip${state?.currentTip}Text"                
		component = (heatStages==1)? "Furnace" : "${heatStages}-stage Furnace"  			                
		sendEvent name: attribute, value: "Observation: You have a ${heatStages}-stage heating system. Here are some of your current settings for auxHeat: stage1HeatingDifferentialTemp=${stage1HeatingDifferentialTemp} " +  
			"which is the difference between current temperature and set-point that will trigger stage-2 heating and stage1HeatingDissipationTime=${stage1HeatingDissipationTime} seconds, which is the time after a heating cycle that the fan will run for to extract any heating left in the system - 30 second default." + 
			"Current tip is: " + recommendation.text,displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Level"                
		sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Solution"                
		sendEvent name: attribute, value: "tip26",displayed: (settings.trace?:false)

	} /* end of tip26 logic */  
	if ((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip27")) && (hasHumidifier=='true') && 
		(currentIndoorHum < (targetHumidity -HUMIDITY_DIFF_ALLOWED))) {	        
		recommendation= get_recommendation('tip27')
		tipsAlreadyGiven.add('tip27')                
		state?.currentTip=state?.currentTip +1             
		attribute ="tip${state?.currentTip}Text"                
		sendEvent name: attribute, value: "Observation: Your indoor humidity is currently ${currentIndoorHum}% " + 
				"which is well below your ideal indoor humidity of ${targetHumidity}% based on the current outdoor temperature of ${outdoorTemp} degrees. Current tip is: " + recommendation.text,
			displayed: (settings.trace?:false)                
		attribute ="tip${state?.currentTip}Level"                
		sendEvent name: attribute, value: recommendation.level,	displayed: (settings.trace?:false)                
		attribute ="tip${state?.currentTip}Solution"                
		sendEvent name: attribute, value: "tip27",displayed: (settings.trace?:false)
	}    /* end of tip27 logic */   
    
	if ((state?.currentTip < maxTips) && (devOutdoorTemperature.toFloat() <= MAX_DEVIATION_OUTDOOR_TEMP)) {
		if (((state?.currentTip < maxTips) &&  (!tipsAlreadyGiven.contains("tip31")) && (dailyHeatingRuntime && avgWeeklyHeatingRuntime) && 
			(dailyHeatingRuntime > avgWeeklyHeatingRuntime))) {
			recommendation= get_recommendation('tip31')
			tipsAlreadyGiven.add('tip31')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			component = (heatStages==1)? "Furnace" : "${heatStages}-stage Furnace"  			                
			sendEvent name: attribute, value: "Observation:The average outdoor temperature has been ${avgOutdoorTemperature} degrees in the past week with a standard deviation of ${devOutdoorTemperature}. Your ${component} Runtime was ${dailyHeatingRuntime} minutes yesterday which is higher than " + 
				"the weekly average Runtime of ${avgWeeklyHeatingRuntime} minutes. Current tip is: " + recommendation.text,
				displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip31,heating",displayed: (settings.trace?:false)
		} /* end of tip31 logic */       
		if ((state?.currentTip < maxTips) && ((dailyCoolingRuntime && avgWeeklyCoolingRuntime) &&
			(dailyCoolingRuntime > avgWeeklyCoolingRuntime))) {
			recommendation= get_recommendation('tip31')
			tipsAlreadyGiven.add('tip31')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			component = (coolStages==1)? "A/C" : "${coolStages}-stage A/C"  			                
			sendEvent name: attribute, value: "Observation: The average outdoor temperature has been ${avgOutdoorTemperature} degrees in the past week with a standard deviation of ${devOutdoorTemperature}. Your ${component} Runtime was ${dailyCoolingRuntime} minutes yesterday which is higher than " + 
				"the weekly average Runtime of ${avgWeeklyCoolingRuntime} minutes. Current tip is: " + recommendation.text,
				displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip31,cooling",displayed: (settings.trace?:false)
		} /* end of tip31 logic */       
		if ((state?.currentTip < maxTips) && ((dailyCoolingRuntime && avgMonthlyCoolingRuntime) && 
			(dailyCoolingRuntime > avgMonthlyCoolingRuntime))) {
			recommendation= get_recommendation('tip31')
			tipsAlreadyGiven.add('tip31')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			component = (coolStages==1)?"A/C": "${coolStages}-stage A/C "  			                
			sendEvent name: attribute, value: "Observation: The average outdoor temperature has been ${avgOutdoorTemperature} degrees in the past week with a standard deviation of ${devOutdoorTemperature}. Your ${component} Runtime was ${dailyCoolingRuntime} minutes yesterday which is higher than " + 
				"the monthly average Runtime of ${avgMonthlyCoolingRuntime} minutes. Current tip is: " + recommendation.text,
				displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip31,cooling",displayed: (settings.trace?:false)
		} /* end of tip31 logic */       
		if ((state?.currentTip < maxTips) && ((dailyHeatingRuntime && avgMonthlyHeatingRuntime) && 
			(dailyHeatingRuntime > avgMonthlyHeatingRuntime))) {
			recommendation= get_recommendation('tip31')
			tipsAlreadyGiven.add('tip31')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			component = (heatStages==1)? "Furnace" : "${heatStages}-stage Furnace"  			                
			sendEvent name: attribute, value: "Observation: The average outdoor temperature has been ${avgOutdoorTemperature} degrees in the past week with a standard deviation of ${devOutdoorTemperature}. Your ${component} Runtime was ${dailyHeatingRuntime} minutes yesterday which is higher than " + 
				"the monthly average runtime of ${avgMonthlyHeatingRuntime} minutes. Current tip is: " + recommendation.text,
				displayed: (settings.trace?:false)
                
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip31,heating",displayed: (settings.trace?:false)
		} /* end of tip31 logic */       
		if (((!tipsAlreadyGiven.contains("tip30")) && (dailyCoolingRuntime && yesterdayCoolingRuntime && avgWeeklyCoolingRuntime)) && 
 			((dailyCoolingRuntime > yesterdayCoolingRuntime ) ||
			(dailyCoolingRuntime> MAX_COOLING_MINUTES_TIME) ||         
			(dailyCoolingRuntime > avgWeeklyCoolingRuntime))) {
			recommendation= get_recommendation('tip30')
			tipsAlreadyGiven.add('tip30')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			component = (coolStages==1)? "A/C" : "${coolStages}-stage A/C"  			                
			sendEvent name: attribute, value: "Observation: The average outdoor temperature has been ${avgOutdoorTemperature} degrees in the past week with a standard deviation of ${devOutdoorTemperature}. Your ${component} Runtime was ${dailyCoolingRuntime} minutes yesterday which is higher than the day before " + 
				"($yesterdayCoolingRuntime minutes) or your A/C runs more than ${MAX_COOLING_MINUTES_TIME} minutes a day or your daily A/C Runtime is higher than the average weekly Runtime of ${avgWeeklyCoolingRuntime} minutes. Current tip is: " + recommendation.text,
				displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip30,cooling",displayed: (settings.trace?:false)
		}
		if (((!tipsAlreadyGiven.contains("tip30")) && (dailyHeatingRuntime && yesterdayHeatingRuntime && avgWeeklyHeatingRuntime)) && 
 			((dailyHeatingRuntime > yesterdayHeatingRuntime  ) ||
			(dailyHeatingRuntime> MAX_HEATING_MINUTES_TIME) ||         
			(dailyHeatingRuntime > avgWeeklyHeatingRuntime))) {
			recommendation= get_recommendation('tip30')
			tipsAlreadyGiven.add('tip30')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			component = (heatStages==1)? "Furnace" : "${heatStages}-stage Furnace"  			                
			sendEvent name: attribute, value: "Observation: The average outdoor temperature has been ${avgOutdoorTemperature} degrees in the past week with a standard deviation of ${devOutdoorTemperature}. Your ${component} Runtime was ${dailyHeatingRuntime} minutes yesterday which is higher than the day before " + 
				"($yesterdayHeatingRuntime minutes) or your Furnace runs more than ${MAX_HEATING_MINUTES_TIME} minutes a day or your daily Furnace Runtime is higher than the average weekly Runtime of ${avgWeeklyHeatingRuntime} minutes. Current tip is: " + recommendation.text,
				displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip30,heating",displayed: (settings.trace?:false)
		}
		if (((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip31")) && (dailyCoolingRuntime && yesterdayCoolingRuntime)) && 
 			((dailyCoolingRuntime  > yesterdayCoolingRuntime ) ||
			(dailyCoolingRuntime > MAX_COOLING_MINUTES_TIME))) {        
			recommendation= get_recommendation('tip31')
			tipsAlreadyGiven.add('tip31')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			component = (coolStages==1)? "A/C" : "${coolStages}-stage A/C"  			                
			sendEvent name: attribute, value: "Observation: The average outdoor temperature has been ${avgOutdoorTemperature} degrees in the past week with a standard deviation of ${devOutdoorTemperature}. Your ${component} Runtime was ${dailyCoolingRuntime} minutes yesterday which is higher than the day before " + 
				"($yesterdayCoolingRuntime minutes) or your A/C runs more than ${MAX_COOLING_MINUTES_TIME} minutes a day. Current tip is: " + recommendation.text,
				displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip31,cooling",displayed: (settings.trace?:false)
		}              
		if (((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip31")) && (dailyHeatingRuntime  && yesterdayHeatingRuntime)) &&  
			((dailyHeatingRuntime  > yesterdayHeatingRuntime ) ||         
			 (dailyHeatingRuntime > MAX_HEATING_MINUTES_TIME))) {        
			recommendation= get_recommendation('tip31')
			tipsAlreadyGiven.add('tip31')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			component = (heatStages==1)? "Furnace" : "${heatStages}-stage Furnace"  			                
			sendEvent name: attribute, value: "Observation: The average outdoor temperature has been ${avgOutdoorTemperature} degrees in the past week with a standard deviation of ${devOutdoorTemperature}. Your ${component} Runtime was ${dailyHeatingRuntime} minutes yesterday which is higher than the day before " + 
				"($yesterdayHeatingRuntime minutes) or your Furnace runs more than ${MAX_HEATING_MINUTES_TIME} minutes a day. Current tip is: " + recommendation.text,
				displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip31,heating",displayed: (settings.trace?:false)
		} /* end of tip31 logic */       
		if (((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip32")) && (dailyCoolingRuntime && yesterdayCoolingRuntime && avgWeeklyCoolingRuntime)) && 
 			((dailyCoolingRuntime > yesterdayCoolingRuntime  ) ||
			(dailyCoolingRuntime> MAX_COOLING_MINUTES_TIME) ||         
			(dailyCoolingRuntime > avgWeeklyCoolingRuntime))) {
			recommendation= get_recommendation('tip32')
			tipsAlreadyGiven.add('tip32')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			component = (coolStages==1)? "A/C" : "${coolStages}-stage A/C"  			                
			sendEvent name: attribute, value: "Observation: The average outdoor temperature has been ${avgOutdoorTemperature} degrees in the past week with a standard deviation of ${devOutdoorTemperature}. Your ${component} Runtime was ${dailyCoolingRuntime} minutes yesterday which is higher than the day before " + 
				"($yesterdayCoolingRuntime minutes) or your A/C runs more than ${MAX_COOLING_MINUTES_TIME} minutes a day or your daily A/C Runtime is higher than the average weekly Runtime of ${avgWeeklyCoolingRuntime} minutes. Current tip is: " + recommendation.text,
				displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip32",displayed: (settings.trace?:false)
		}
	}        
	traceEvent(settings.logFilter,"get_tips_level3>end with tipsAlreadyGiven=$tipsAlreadyGiven,state.tips=${state?.tips}",settings.trace)    
	return tipsAlreadyGiven    
}   /* end getLevel 3 */  

private def get_tips_level4() {
	def tipsAlreadyGiven = (state?.tips != [])? state.tips: []
	traceEvent(settings.logFilter,"get_tips_level4>begin with tipsAlreadyGiven=$tipsAlreadyGiven,state.tips=${state?.tips}",settings.trace)
	def scale = state?.scale
	def maxTips=get_MAX_TIPS()    
	def recommendation=null
	def attribute,component   
	boolean isWeekday=isWeekday()    
/*
	def MAX_DEVIATION_TEMPERATURE= (scale=='F')?3:1.5
*/    
	float MAX_DEVIATION_TEMPERATURE= (scale=='F')?0.6:0.3
	float MAX_DIFFERENCE_TEMPERATURE=(scale=='F')?3:1.5
	float MIN_DEVIATION_COOLING_SETPOINT= (scale=='F')?6:3
	float MIN_DEVIATION_HEATING_SETPOINT= (scale=='F')?6:3
	int MAX_HEATING_CYCLE=10
	int MAX_COOLING_CYCLE=10
	int MAX_COOLING_MINUTES_TIME=600    
	int MAX_HEATING_MINUTES_TIME=300    
	float MAX_DEVIATION_OUTDOOR_TEMP=(scale=='F')?10:5
	int MAX_HUMIDITY_LEVEL=55
	int HUMIDITY_DIFF_ALLOWED=5
	int MILLISECONDS_PER_HOUR=(60 * 60 * 1000)    
	float RATIO_MIN_AUX_HEAT= 20
	float RATIO_MIN_AUX_COOL= 20
	float RATIO_MIN_AUX_COOL_FOR_HIGH_TEMP=40   
	float RATIO_MIN_AUX_HEAT_FOR_LOW_TEMP=5
	float LOW_TEMP_THRESHOLD_FOR_HEATING=(scale=='F')?15:-10    
	float HIGH_TEMP_THRESHOLD_FOR_COOLING=(scale=='F')?80:26    
    
	Date endDate= new Date()
	Date startDate = endDate -1
	Date aWeekAgo=endDate-7
	int heatStages = device.currentValue("heatStages")?.toInteger()
	int coolStages = device.currentValue("coolStages")?.toInteger()
	String supportedThermostatModes=getSupportedThermostatModes()
	if ((heatStages==0) && (supportedThermostatModes.contains("auxHeatOnly"))) heatStages=1
    
 	def devOutdoorTemperature=get_stats_attribute("weatherTemperature",aWeekAgo,endDate,'deviation')
	def avgOutdoorTemperature=get_stats_attribute("weatherTemperature",aWeekAgo,endDate,'avg')

	if (settings.trace) {
		log.debug("get_tips_level4>About to get heating variables")
	}
	def dailyHeatingRuntimeAux1 = device.currentValue("auxHeat1RuntimeDaily")?.toFloat()   
	def dailyHeatingRuntimeComp1= device.currentValue("compHeat1RuntimeDaily")?.toFloat()
	def yesterdayHeatingRuntimeAux1 = device.currentValue("auxHeat1RuntimeYesterday")?.toFloat()
	def yesterdayHeatingRuntimeComp1=device.currentValue("compHeat1RuntimeYesterday")?.toFloat()
	def avgWeeklyHeatingRuntimeAux1 = device.currentValue("auxHeat1RuntimeAvgWeekly")?.toFloat()
	def avgWeeklyHeatingRuntimeComp1= device.currentValue("compHeat1RuntimeAvgWeekly")?.toFloat()    
	def avgMonthlyHeatingRuntimeAux1 = device.currentValue("auxHeat1RuntimeAvgMonthly")?.toFloat()
	def avgMonthlyHeatingRuntimeComp1= device.currentValue("compHeat1RuntimeAvgMonthly")?.toFloat()   
	def dailyHeatingRuntimeAux2 = device.currentValue("auxHeat2RuntimeDaily")?.toFloat()
	def dailyHeatingRuntimeComp2=device.currentValue("compHeat2RuntimeDaily")?.toFloat()  
	def yesterdayHeatingRuntimeAux2 = device.currentValue("auxHeat2RuntimeYesterday")?.toFloat()
	def yesterdayHeatingRuntimeComp2=device.currentValue("compHeat2RuntimeYesterday")?.toFloat()
	def avgWeeklyHeatingRuntimeAux2  = device.currentValue("auxHeat2RuntimeAvgWeekly")?.toFloat()
	def avgWeeklyHeatingRuntimeComp2= device.currentValue("compHeat2RuntimeAvgWeekly")?.toFloat()    
	def avgMonthlyHeatingRuntimeAux2  = device.currentValue("auxHeat2RuntimeAvgMonthly")?.toFloat()
	def avgMonthlyHeatingRuntimeComp2= device.currentValue("compHeat2RuntimeAvgMonthly")?.toFloat()   
	def dailyHeatingRuntimeAux3  = device.currentValue("auxHeat3RuntimeDaily")?.toFloat()
	def dailyHeatingRuntimeComp3=device.currentValue("compHeat3RuntimeDaily")?.toFloat()  
	def yesterdayHeatingRuntimeAux3  = device.currentValue("auxHeat3RuntimeYesterday")?.toFloat()
	def yesterdayHeatingRuntimeComp3=device.currentValue("compHeat3RuntimeYesterday")?.toFloat()
	def avgWeeklyHeatingRuntimeAux3 = device.currentValue("auxHeat3RuntimeAvgWeekly")?.toFloat()
	def avgWeeklyHeatingRuntimeComp3= device.currentValue("compHeat3RuntimeAvgWeekly")?.toFloat()    
	def avgMonthlyHeatingRuntimeAux3 = device.currentValue("auxHeat3RuntimeAvgMonthly")?.toFloat()
	def avgMonthlyHeatingRuntimeComp3= device.currentValue("compHeat3RuntimeAvgMonthly")?.toFloat()   

	if (settings.trace) {
		log.debug "get_tips_level4>About to get cooling variables"
	}
	def dailyCoolingRuntimeCool1 = device.currentValue("compCool1RuntimeDaily")?.toFloat()
	def yesterdayCoolingRuntimeCool1 = device.currentValue("compCool1RuntimeYesterday")?.toFloat()
	def avgWeeklyCoolingRuntimeCool1 = device.currentValue("compCool1RuntimeAvgWeekly")?.toFloat()
	def avgMonthlyCoolingRuntimeCool1 = device.currentValue("compCool1RuntimeAvgMonthly")?.toFloat()
	def dailyCoolingRuntimeCool2 = device.currentValue("compCool2RuntimeDaily")?.toFloat()
	def yesterdayCoolingRuntimeCool2 = device.currentValue("compCool2RuntimeYesterday")?.toFloat()
	def avgWeeklyCoolingRuntimeCool2 = device.currentValue("compCool2RuntimeAvgWeekly")?.toFloat()
	def avgMonthlyCoolingRuntimeCool2 = device.currentValue("compCool2RuntimeAvgMonthly")?.toFloat()
	def fanRuntime = device.currentValue("fanRuntimeDaily")?.toFloat()
	def hasDehumidifier = (device.currentValue("hasDehumidifier")) ?: 'false'
	def hasHumidifier = (device.currentValue("hasHumidifier")) ?: 'false'
	def hasHrv = (device.currentValue("hasHrv")) ?: 'false'
	def hasErv = (device.currentValue("hasErv")) ?: 'false'
	if (settings.trace) {
		log.debug "get_tips_level4>before calculations"
		log.debug "get_tips_level4>dailyHeatingRuntimeAux1=$dailyHeatingRuntimeAux1 minutes"
		log.debug "get_tips_level4>dailyHeatingRuntimeComp1=$dailyHeatingRuntimeComp1 minutes"
	}
    
	float dailyCoolingRuntime =  (((dailyCoolingRuntimeCool1)?:0) + ((dailyCoolingRuntimeCool2)?:0)).toFloat().round(1)
	float yesterdayCoolingRuntime = (((yesterdayCoolingRuntimeCool1)?:0) + ((yesterdayCoolingRuntimeCool2)?:0)).toFloat().round(1)
	float avgWeeklyCoolingRuntime = (((avgWeeklyCoolingRuntimeCool1)?:0) + ((avgWeeklyCoolingRuntimeCool2)?:0)).toFloat().round(1)
	float avgMonthlyCoolingRuntime = (((avgMonthlyCoolingRuntimeCool1)?:0) + ((avgMonthlyCoolingRuntimeCool2)?:0)).toFloat().round(1)
	float dailyHeatingRuntime = (((dailyHeatingRuntimeComp1)?:0) + ((dailyHeatingRuntimeComp2)?:0) + ((dailyHeatingRuntimeComp3)?:0) + 
		(dailyHeatingRuntimeAux1)?:0) + ((dailyHeatingRuntimeAux2)?:0) + ((dailyHeatingRuntimeAux3)?:0).toFloat().round(1)
	float yesterdayHeatingRuntime = (((yesterdayHeatingRuntimeComp1)?:0) + ((yesterdayHeatingRuntimeComp2)?:0) + ((yesterdayHeatingRuntimeComp3)?:0) +
 		((yesterdayHeatingRuntimeAux1)?:0) + ((yesterdayHeatingRuntimeAux2)?:0) + ((yesterdayHeatingRuntimeAux3)?:0)).toFloat().round(1)
	float avgWeeklyHeatingRuntime = (((avgWeeklyHeatingRuntimeComp1)?:0) + ((avgWeeklyHeatingRuntimeComp2)?:0) + ((avgWeeklyHeatingRuntimeComp3)?:0) +
 		((avgWeeklyHeatingRuntimeAux1)?:0) + ((avgWeeklyHeatingRuntimeAux2)?:0) + ((avgWeeklyHeatingRuntimeAux3)?:0)).toFloat().round(1)
	float avgMonthlyHeatingRuntime = (((avgMonthlyHeatingRuntimeComp1)?:0) + ((avgMonthlyHeatingRuntimeComp2)?:0) + 
		((avgMonthlyHeatingRuntimeComp3)?:0) + ((avgMonthlyHeatingRuntimeAux1)?:0) + ((avgMonthlyHeatingRuntimeAux2)?:0) + 
		((avgMonthlyHeatingRuntimeAux3)?:0)).toFloat().round(1)

	if (settings.trace) {
		traceEvent(settings.logFilter,"get_tips_level4>avgMonthlyHeatingRuntime=$avgMonthlyHeatingRuntime minutes", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level4>avgWeeklyHeatingRuntime=$avgWeeklyHeatingRuntime minutes", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level4>yesterdayHeatingRuntime=$yesterdayHeatingRuntime minutes", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level4>dailyHeatingRuntime=$dailyHeatingRuntime minutes", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level4>avgMonthlyCoolingRuntime=$avgMonthlyCoolingRuntime minutes", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level4>avgWeeklyCoolingRuntime=$avgWeeklyCoolingRuntime minutes", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level4>yesterdayCoolingRuntime=$yesterdayCoolingRuntime minutes", settings.trace)
		traceEvent(settings.logFilter,"get_tips_level4>dailyCoolingRuntime=$dailyCoolingRuntime minutes", settings.trace)
	}
	traceEvent(settings.logFilter,"get_tips_level4>About to calculate Aux Heating ratios",settings.trace)
	float heatAuxRuntimeDaily=0,heatAuxRuntimeYesterday=0,heatAuxRuntimeAvgWeekly=0,heatAuxRuntimeAvgMonthly=0  
	if (heatStages> 2) {    
		heatAuxRuntimeDaily= ((dailyHeatingRuntimeAux1?:0) + (dailyHeatingRuntimeAux2?:0)).toFloat().round(1)
		heatAuxRuntimeYesterday=((yesterdayHeatingRuntimeAux1?:0) + (yesterdayHeatingRuntimeAux2?:0)).toFloat().round(1)
		heatAuxRuntimeAvgWeekly=((avgWeeklyHeatingRuntimeAux1?:0) + (avgWeeklyHeatingRuntimeAux2?:0)).toFloat().round(1)
		heatAuxRuntimeAvgMonthly=((avgMonthlyHeatingRuntimeAux1?:0) + (avgMonthlyHeatingRuntimeAux2?:0)).toFloat().round(1)
	} else {        
		heatAuxRuntimeDaily= (dailyHeatingRuntimeAux1?:0)
		heatAuxRuntimeYesterday=(yesterdayHeatingRuntimeAux1?:0)
		heatAuxRuntimeAvgWeekly=(avgWeeklyHeatingRuntimeAux1?:0)
		heatAuxRuntimeAvgMonthly=(avgMonthlyHeatingRuntimeAux1?:0) 
	}    
	if (settings.trace) {
		sendEvent  name: "verboseTrace",value:"get_tips_level4>heatAuxRuntimeDaily=$heatAuxRuntimeDaily minutes"
		sendEvent  name: "verboseTrace", value: "get_tips_level4>heatAuxRuntimeYesterday=$heatAuxRuntimeYesterday minutes"
		sendEvent  name: "verboseTrace",value:"get_tips_level4>heatAuxRuntimeAvgWeekly=$heatAuxRuntimeAvgWeekly minutes"
		sendEvent  name: "verboseTrace",value:"get_tips_level4>heatAuxRuntimeAvgMonthly=$heatAuxRuntimeAvgMonthly minutes"
	}        
	float ratioAuxDaily=((heatAuxRuntimeDaily/dailyHeatingRuntime)*100).round(1)
	traceEvent(settings.logFilter,"get_tips_level4>ratioAuxHeatDaily=$ratioAuxDaily %",settings.trace)
	float ratioAuxYesterday=((heatAuxRuntimeYesterday/yesterdayHeatingRuntime)*100).round(1)
	traceEvent(settings.logFilter,"get_tips_level4>ratioAuxHeatYesterday=$ratioAuxYesterday %",settings.trace)
	float ratioAuxWeekly=((heatAuxRuntimeAvgWeekly/avgWeeklyHeatingRuntime)*100).round(1)
	traceEvent(settings.logFilter,"get_tips_level4>ratioAuxHeatWeekly=$ratioAuxWeekly %",settings.trace)
	float ratioAuxMonthly=((heatAuxRuntimeAvgMonthly/avgMonthlyHeatingRuntime)*100).round(1)
	traceEvent(settings.logFilter,"get_tips_level4>ratioAuxHeatMonthly=$ratioAuxMonthly %",settings.trace)
	def hasHeatPump = (device.currentValue("hasHeatPump")) ?: 'false'

	if (supportedThermostatModes.contains("auxHeatOnly")) {
    
		if ((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip40")) &&  (dailyHeatingRuntime && heatAuxRuntimeDaily) && (((avgOutdoorTemperature > LOW_TEMP_THRESHOLD_FOR_HEATING)) &&
			(ratioAuxDaily < RATIO_MIN_AUX_HEAT)) || (((avgOutdoorTemperature <=  LOW_TEMP_THRESHOLD_FOR_HEATING)) &&
			(ratioAuxDaily < RATIO_MIN_AUX_HEAT_FOR_LOW_TEMP))) {            
			recommendation= get_recommendation('tip40')
			tipsAlreadyGiven.add('tip40')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			component = (heatStages>2)? "Aux Heat Stage 1 and 2" : "Aux Heat Stage 1"  			                
			sendEvent name: attribute, value: "Observation: The average outdoor temperature in the past week has been ${avgOutdoorTemperature} degrees with a standard deviation of ${devOutdoorTemperature} degrees. "+
				"You have a ${heatStages}-stage heating system and yesterday's ${component} ratio of ${ratioAuxDaily}% usage versus the Total Heating Runtime is lower than ${RATIO_MIN_AUX_HEAT}%." +
				"Yesterday's Auxiliary Heat Runtime is ${heatAuxRuntimeDaily} versus the total Heating Runtime of ${dailyHeatingRuntime} minutes." +  
				"Current tip is: " + recommendation.text,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip40",displayed: (settings.trace?:false)
		} /* end of tip40 logic */  
	} /* end of auxHeat logic */        
	if ((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip41")) &&  (yesterdayHeatingRuntime) && (ratioAuxDaily < ratioAuxYesterday)) {            
		recommendation= get_recommendation('tip41')
		tipsAlreadyGiven.add('tip41')                
		state?.currentTip=state?.currentTip +1             
		attribute ="tip${state?.currentTip}Text"                
		component = (heatStages>2)? "Aux Heat Stage 1 and 2" : "Aux Heat Stage 1"  			                
		sendEvent name: attribute, value: "Observation: The average outdoor temperature in the past week has been ${avgOutdoorTemperature} degrees with a standard deviation of ${devOutdoorTemperature} degrees." +
			"You have a ${heatStages}-stage heating system and yesterday's ${component} ratio of ${ratioAuxDaily}% usage versus the Total Heating Runtime is lower than the day before's ratio of ${ratioAuxYesterday}%." +  
			"Yesterday's Auxiliary Heat Runtime is ${heatAuxRuntimeDaily} versus the day before's Heat Aux Runtime of ${heatAuxRuntimeYesterday} minutes." +
			"Current tip is: " + recommendation.text,displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Level"                
		sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Solution"                
		sendEvent name: attribute, value: "tip41",displayed: (settings.trace?:false)
	} /* end of tip41 logic */                   
	if ((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip42")) &&  (avgWeeklyHeatingRuntime) && (ratioAuxDaily < ratioAuxWeekly)) {            
		recommendation= get_recommendation('tip42')
		tipsAlreadyGiven.add('tip42')                
		state?.currentTip=state?.currentTip +1             
		attribute ="tip${state?.currentTip}Text"                
		component = (heatStages>2)? "Aux Heat Stage 1 and 2" : "Aux Heat Stage 1"  			                
		sendEvent name: attribute, value: "Observation: The average outdoor temperature in the past week has been ${avgOutdoorTemperature} degrees with a standard deviation of ${devOutdoorTemperature} degrees." +
			"You have a ${heatStages}-stage heating system and the yesterday's ${component} ratio of ${ratioAuxDaily}% usage versus the Total Heating Runtime is lower than the weekly average ratio of ${ratioAuxWeekly}%." +  
			"Yesterday's Auxiliary Heat Runtime is ${heatAuxRuntimeDaily} versus the weekly Average Auxiliary Heat Runtime of ${heatAuxRuntimeAvgWeekly} minutes." +
			"Current tip is: " + recommendation.text,displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Level"                
		sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Solution"                
		sendEvent name: attribute, value: "tip42",displayed: (settings.trace?:false)
	} /* end of tip42 logic */                   
	if ((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip43")) &&  (avgMonthlyHeatingRuntime) && (ratioAuxDaily < ratioAuxMonthly)) {            
		recommendation= get_recommendation('tip43')
		tipsAlreadyGiven.add('tip43')                
		state?.currentTip=state?.currentTip +1             
		attribute ="tip${state?.currentTip}Text"                
		component = (heatStages>2)? "Aux Heat Stage 1 and 2" : "Aux Heat Stage 1"  			                
		sendEvent name: attribute, value: "Observation: The average outdoor temperature in the past week has been ${avgOutdoorTemperature} degrees with a standard deviation of ${devOutdoorTemperature} degrees." +
			"You have a ${heatStages}-stage heating system and yesterday's ${component} ratio of ${ratioAuxDaily}% usage versus the Total Heating Runtime is lower than the monthly average ratio of ${ratioAuxMonthly}%." +  
			"Yesterday's Auxiliary Heat Runtime is ${heatAuxRuntimeDaily} versus the monthly Average Auxiliary Heat Runtime of ${heatAuxRuntimeAvgMonthly} minutes." +
			"Current tip is: " + recommendation.text,displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Level"                
		sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
		attribute ="tip${state?.currentTip}Solution"                
		sendEvent name: attribute, value: "tip43",displayed: (settings.trace?:false)
	} /* end of tip43 logic */       

	
	if (heatStages> 2) {    
		heatAuxRuntimeDaily= ((dailyHeatingRuntimeComp1?:0) + (dailyHeatingRuntimeComp2?:0)).toFloat().round(1)
		heatAuxRuntimeYesterday=((yesterdayHeatingRuntimeComp1?:0) + (yesterdayHeatingRuntimeComp2?:0)).toFloat().round(1)
		heatAuxRuntimeAvgWeekly=((avgWeeklyHeatingRuntimeComp1?:0) + (avgWeeklyHeatingRuntimeComp2?:0)).toFloat().round(1)
		heatAuxRuntimeAvgMonthly=((avgMonthlyHeatingRuntimeComp1?:0) + (avgMonthlyHeatingRuntimeComp2?:0)).toFloat().round(1)
	} else {        
		heatAuxRuntimeDaily= (dailyHeatingRuntimeComp1?:0)
		heatAuxRuntimeYesterday=(yesterdayHeatingRuntimeComp1?:0)
		heatAuxRuntimeAvgWeekly=(avgWeeklyHeatingRuntimeComp1?:0)
		heatAuxRuntimeAvgMonthly=(avgMonthlyHeatingRuntimeComp1?:0) 
	}    

	if (heatStages>1) { 
		traceEvent(settings.logFilter,"get_tips_level4>About to calculate Heating ratios",settings.trace)
		ratioAuxDaily=0 
		ratioAuxYesterday=0
		ratioAuxWeekly=0
		ratioAuxMonthly=0        
		if (heatAuxRuntimeDaily) {
			ratioAuxDaily=((heatAuxRuntimeDaily/dailyHeatingRuntime)*100).round(1)
		}            
		traceEvent(settings.logFilter,"get_tips_level4>ratioHeatComp1Daily=$ratioAuxDaily %",settings.trace)
		if (heatAuxRuntimeYesterday) {
			ratioAuxYesterday=((heatAuxRuntimeYesterday/yesterdayHeatingRuntime)*100).round(1)
		}            
		traceEvent(settings.logFilter,"get_tips_level4>ratioHeatComp1Yesterday=$ratioAuxYesterday %",settings.trace)
		if (heatAuxRuntimeAvgWeekly) {
			ratioAuxWeekly=((heatAuxRuntimeAvgWeekly/avgWeeklyHeatingRuntime)*100).round(1)
		}            
		traceEvent(settings.logFilter,"get_tips_level4>ratioHeatComp1Weekly=$ratioAuxWeekly %",settings.trace)
		if (heatAuxRuntimeAvgMonthly) {
			ratioAuxMonthly=((heatAuxRuntimeAvgMonthly/avgMonthlyHeatingRuntime)*100).round(1)
		}            
		traceEvent(settings.logFilter,"get_tips_level4>ratioHeatComp1Monthly=$ratioAuxMonthly %",settings.trace)

		component = (heatStages>2)? "Comp Heat Stage 1 and 2" : "Comp Heat Stage 1"  			                
		if ((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip40")) &&  (dailyHeatingRuntime && heatAuxRuntimeDaily) && (((avgOutdoorTemperature > LOW_TEMP_THRESHOLD_FOR_HEATING)) &&
			(ratioAuxDaily < RATIO_MIN_AUX_HEAT)) || (((avgOutdoorTemperature <=  LOW_TEMP_THRESHOLD_FOR_HEATING)) &&
			(ratioAuxDaily < RATIO_MIN_AUX_HEAT_FOR_LOW_TEMP))) {            
			recommendation= get_recommendation('tip44')
			tipsAlreadyGiven.add('tip44')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			sendEvent name: attribute, value: "Observation: The average outdoor temperature in the past week has been ${avgOutdoorTemperature} degrees with a standard deviation of ${devOutdoorTemperature} degrees." +
				"You have a ${heatStages}-stage heating system and yesterday's ${component} ratio of ${ratioAuxDaily}% usage versus the Total Heating Runtime is lower than ${RATIO_MIN_AUX_HEAT}%." +  
				"Yesterday's ${component} Runtime is ${heatAuxRuntimeDaily} versus the total Heating Runtime of ${dailyHeatingRuntime} minutes." +  
				"Current tip is: " + recommendation.text,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip44",displayed: (settings.trace?:false)
		} /* end of tip44 logic */                   
		if ((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip45")) && (yesterdayHeatingRuntime) && (ratioAuxDaily < ratioAuxYesterday)) {            
			recommendation= get_recommendation('tip45')
			tipsAlreadyGiven.add('tip45')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			sendEvent name: attribute, value: "Observation: The average outdoor temperature in the past week has been ${avgOutdoorTemperature} degrees with a standard deviation of ${devOutdoorTemperature} degrees." +
				"You have a ${heatStages}-stage heating system and yesterday's ${component} ratio of ${ratioAuxDaily}% usage versus the total Heating Runtime is lower than the day before's ratio of ${ratioAuxYesterday}%." +  
				"Yesterday's ${component} Runtime is ${heatAuxRuntimeDaily} versus the day before's ${component} Runtime of ${heatAuxRuntimeYesterday} minutes." +  
				"Current tip is: " + recommendation.text,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip45",displayed: (settings.trace?:false)
		} /* end of tip45 logic */                   
		if ((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip46")) &&  (avgWeeklyHeatingRuntime) && (ratioAuxDaily < ratioAuxWeekly)) {            
			recommendation= get_recommendation('tip46')
			tipsAlreadyGiven.add('tip46')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			sendEvent name: attribute, value: "Observation: The average outdoor temperature in the past week has been ${avgOutdoorTemperature} degrees with a standard deviation of ${devOutdoorTemperature} degrees." +
				"You have a ${heatStages}-stage heating system and yesterday's ${component} ratio of ${ratioAuxDaily}% usage versus the total Heating Runtime is lower than the weekly average ratio of ${ratioAuxWeekly}%." +  
				"Yesterday's ${component}Runtime is ${heatAuxRuntimeDaily} versus the weekly Average ${component} Runtime of ${heatAuxRuntimeAvgWeekly} minutes." +  
				"Current tip is: " + recommendation.text,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip46",displayed: (settings.trace?:false)
		} /* end of tip46 logic */                   
		if ((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip47")) &&  (avgMonthlyHeatingRuntime) && (ratioAuxDaily < ratioAuxMonthly)) {            
			recommendation= get_recommendation('tip47')
			tipsAlreadyGiven.add('tip47')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			sendEvent name: attribute, value: "Observation: The average outdoor temperature in the past week has been ${avgOutdoorTemperature} degrees with a standard deviation of ${devOutdoorTemperature} degrees." +
				"You have a ${heatStages}-stage heating system and yesterday's ${component} ratio of ${ratioAuxDaily}% usage versus the total Heating Runtime is lower than the monthly average ratio of ${ratioAuxMonthly}%." +  
				"Yesterday's ${component} Runtime is ${heatAuxRuntimeDaily} versus the monthly average ${component} Runtime of ${heatAuxRuntimeAvgMonthly} minutes." +  
				"Current tip is: " + recommendation.text,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level, displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip47",displayed: (settings.trace?:false)
		} /* end of tip47 logic */                   
	} /* end heatStages logic */                   
	
	if (coolStages>1) { 

		component = "Comp A/C Stage 1"  			                
		ratioAuxDaily=((dailyCoolingRuntimeCool1/dailyCoolingRuntime)*100).round(1)
		traceEvent(settings.logFilter,"get_tips_level4>ratioAuxCoolDaily=$ratioAuxDaily %",settings.trace)
		ratioAuxYesterday=((yesterdayCoolingRuntimeCool1/yesterdayCoolingRuntime)*100).round(1)
		traceEvent(settings.logFilter,"get_tips_level4>ratioAuxCoolYesterday=$ratioAuxYesterday %",settings.trace)
		ratioAuxWeekly=((avgWeeklyCoolingRuntimeCool1/avgWeeklyCoolingRuntime)*100).round(1)
		traceEvent(settings.logFilter,"get_tips_level4>ratioAuxCoolingWeekly=$ratioAuxWeekly %",settings.trace)
		ratioAuxMonthly=((avgMonthlyCoolingRuntimeCool1/avgMonthlyCoolingRuntime)*100).round(1)
		traceEvent(settings.logFilter,"get_tips_level4>ratioAuxCoolingMonthly=$ratioAuxMonthly %",settings.trace)
		if ((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip44")) &&  (dailyCoolingRuntime) && (((avgOutdoorTemperature > HIGH_TEMP_THRESHOLD_FOR_COOLING)) &&
			(ratioAuxDaily < RATIO_MIN_AUX_COOL)) || (((avgOutdoorTemperature >=  HIGH_TEMP_THRESHOLD_FOR_COOLING)) &&
			(ratioAuxDaily < RATIO_MIN_AUX_COOL_FOR_HIGH_TEMP))) {             
			recommendation= get_recommendation('tip44')
			tipsAlreadyGiven.add('tip44')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			sendEvent name: attribute, value: "Observation: The average outdoor temperature in the past week has been ${avgOutdoorTemperature} degrees with a standard deviation of ${devOutdoorTemperature} degrees." +
				"You have a ${coolStages}-stage cooling system and yesterday's ${component} ratio of ${ratioAuxDaily}% usage versus the Total Cooling Runtime is lower than ${RATIO_MIN_AUX_COOL}%." +  
				"Yesterday's ${component} Runtime is ${dailyCoolingRuntimeCool1} versus the total Cooling Runtime of ${dailyCoolingRuntime} minutes." +  
				"Current tip is: " + recommendation.text,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip44",displayed: (settings.trace?:false)
		} /* end of tip44 logic */                   
		if ((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip45")) && (yesterdayCoolingRuntime) && (ratioAuxDaily < ratioAuxYesterday)) {            
			recommendation= get_recommendation('tip45')
			tipsAlreadyGiven.add('tip45')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			sendEvent name: attribute, value: "Observation: The average outdoor temperature in the past week has been ${avgOutdoorTemperature} degrees with a standard deviation of ${devOutdoorTemperature} degrees." +
				"You have a ${coolStages}-stage cooling system and yesterday's ${component} ratio of ${ratioAuxDaily}% usage versus the total Cooling Runtime is lower than the day before's ratio of ${ratioAuxYesterday}%." +  
				"Yesterday's ${component} Runtime is ${dailyCoolingRuntimeCool1} versus the day before's ${component} Runtime of ${yesterdayCoolingRuntimeCool1} minutes." +  
				"Current tip is: " + recommendation.text,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip45",displayed: (settings.trace?:false)
		} /* end of tip45 logic */                   
		if ((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip46")) &&  (avgWeeklyCoolingRuntime) && (ratioAuxDaily < ratioAuxWeekly)) {            
			recommendation= get_recommendation('tip46')
			tipsAlreadyGiven.add('tip46')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			sendEvent name: attribute, value: "Observation: The average outdoor temperature in the past week has been ${avgOutdoorTemperature} degrees with a standard deviation of ${devOutdoorTemperature} degrees." +
				"You have a ${coolStages}-stage cooling system and yesterday's ${component} ratio of ${ratioAuxDaily}% usage versus the total Cooling Time is lower than the weekly average ratio of ${ratioAuxWeekly}%." +  
				"Yesterday's ${component}Runtime is ${dailyCoolingRuntimeCool1} versus the weekly Average ${component} Runtime of ${avgWeeklyCoolingRuntimeCool1} minutes." +  
				"Current tip is: " + recommendation.text,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip46",displayed: (settings.trace?:false)
		} /* end of tip46 logic */                   
		if ((state?.currentTip < maxTips) && (!tipsAlreadyGiven.contains("tip47")) &&  (avgMonthlyCoolingRuntime) && (ratioAuxDaily < ratioAuxMonthly)) {            
			recommendation= get_recommendation('tip47')
			tipsAlreadyGiven.add('tip47')                
			state?.currentTip=state?.currentTip +1             
			attribute ="tip${state?.currentTip}Text"                
			sendEvent name: attribute, value: "Observation: The average outdoor temperature in the past week has been ${avgOutdoorTemperature} degrees with a standard deviation of ${devOutdoorTemperature} degrees." +
				"You have a ${coolStages}-stage cooling system and yesterday's ${component} ratio of ${ratioAuxDaily}% usage versus the total Cooling Runtime is lower than the monthly average ratio of ${ratioAuxMonthly}%." +  
				"Yesterday's ${component} Runtime is ${dailyCoolingRuntimeCool1} versus the monthly average ${component} Runtime of ${avgMonthlyCoolingRuntimeCool1} minutes." +  
				"Current tip is: " + recommendation.text,displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Level"                
			sendEvent name: attribute, value: recommendation.level, displayed: (settings.trace?:false)
			attribute ="tip${state?.currentTip}Solution"                
			sendEvent name: attribute, value: "tip47",displayed: (settings.trace?:false)
		} /* end of tip47 logic */                   
	}   /* end coolStages logic */     
	traceEvent(settings.logFilter,"get_tips_level4>end with tipsAlreadyGiven=$tipsAlreadyGiven,state.tips=${state?.tips}",settings.trace)
	return tipsAlreadyGiven    
}   /* end getLevel 4 */ 

private int find_ideal_indoor_humidity(outsideTemp) {
	def scale = state?.scale
	float outdoorTemp=(scale=='C')?outsideTemp.toFloat():fToC(outsideTemp.toFloat())	
	// -30C => 30%, at 0C => 45%

	int targetHum = 45 + (0.5 * outdoorTemp)
	return (Math.max(Math.min(targetHum, 60), 30))
}

private def toQueryString(Map m) {
	return m.collect { k, v -> "${k}=${URLEncoder.encode(v.toString())}" }.sort().join("&")
}

private def cToF(temp) {
	return (temp * 1.8 + 32)
}
private def fToC(temp) {
	return (temp - 32).toDouble() / 1.8
}
private def milesToKm(distance) {
	return (distance * 1.609344) 
}
private def get_URI_ROOT() {
	return "https://api.ecobee.com"
}

private def get_API_VERSION() {
	return "1"
}
// Maximum tstat batch size (25 thermostats max may be processed in batch)
private def get_MAX_TSTAT_BATCH() {
	return 25
}

// Maximum number of command retries for setters
private def get_MAX_SETTER_RETRIES() {
	return 10
}


// Maximum number of command retries for getters
private def get_MAX_GETTER_RETRIES() {
	return 2
}

private def get_MAX_REFRESH_TOKENS_DELAY() {

	return 3 * get_RETRY_DELAY_FACTOR() * 60
}

private def get_RETRY_DELAY_FACTOR() {
	return 3.1
}

private def get_MAX_TIPS() {
	return 5
}


private def formatDate(dateString) {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm zzz")
	Date aDate = sdf.parse(dateString)
	return aDate
}

private def formatTimeInTimezone(dateTime, timezone='') {
	def myTimezone=(timezone)?TimeZone.getTimeZone(timezone):location.timeZone 
	String dateInLocalTime =new Date(dateTime).format("yyyy-MM-dd HH:mm:ss zzz", myTimezone)
	return dateInLocalTime
}


private def getCustomImagePath() {
	return "https://raw.githubusercontent.com/yracine/device-type.myecobee/master/icons/"
}

private def getStandardImagePath() {
	return "https://cdn.device-icons.smartthings.com"
}

@Field int GLOBAL_LOG_ERROR=1
@Field int GLOBAL_LOG_WARN= 2
@Field int GLOBAL_LOG_INFO=3
@Field int GLOBAL_LOG_DEBUG=4
@Field int GLOBAL_LOG_TRACE=5

def traceEvent(logFilter,message, displayEvent=false, traceLevel=GLOBAL_LOG_DEBUG, sendMessage=true) {
	int filterLevel=(logFilter)?logFilter.toInteger():GLOBAL_LOG_WARN

	if ((displayEvent) || (sendMessage)) {
		def results = [
			name: "verboseTrace",
			value: message,
			displayed: ((displayEvent)?: false)
		]	

		if ((displayEvent) && (filterLevel >= traceLevel)) {
			switch (traceLevel) {
				case GLOBAL_LOG_ERROR:
					log.error "${message}"
				break
				case GLOBAL_LOG_WARN:
					log.warn "${message}"
				break
				case GLOBAL_LOG_INFO:
					log.info  "${message}"
				break
				case GLOBAL_LOG_TRACE:
					log.trace "${message}"
				break
				case GLOBAL_LOG_DEBUG:
				default:
					log.debug "${message}"
				break
			}  /* end switch*/              
		} /* end if displayEvent*/
		if (sendMessage) sendEvent (results)
	}
}