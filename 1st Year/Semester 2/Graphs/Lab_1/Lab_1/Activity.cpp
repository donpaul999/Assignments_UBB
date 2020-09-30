//
// Created by Paul Colta on 25/04/2020.
//

#include "Activity.h"
Activity::Activity() : nodeIndex(0), duration(0), earliestStartTime(0),
                                                  earliestEndTime(0), latestStartTime(0),
                                                  latestEndTime(0) {}

Activity::Activity(int nodeIndex, int duration) : nodeIndex(nodeIndex), duration(duration), earliestStartTime(0),
                                                  earliestEndTime(0), latestStartTime(0),
                                                  latestEndTime(0) {}

void Activity::setEarliestStartTime(int earliestStartTime) {
    Activity::earliestStartTime = earliestStartTime;
}

void Activity::setEarliestEndTime(int earliestEndTime) {
    Activity::earliestEndTime = earliestEndTime;
}

void Activity::setLatestStartTime(int latestStartTime) {
    Activity::latestStartTime = latestStartTime;
}

void Activity::setLatestEndTime(int latestEndTime) {
    Activity::latestEndTime = latestEndTime;
}

bool Activity::isCritical() const {
    return earliestStartTime == latestStartTime && earliestEndTime == latestEndTime;
}

int Activity::getDuration() const {
    return duration;
}

int Activity::getEarliestStartTime() const {
    return earliestStartTime;
}

int Activity::getEarliestEndTime() const {
    return earliestEndTime;
}

int Activity::getLatestStartTime() const {
    return latestStartTime;
}

int Activity::getLatestEndTime() const {
    return latestEndTime;
}

int Activity::getNodeIndex() const {
    return nodeIndex;
}