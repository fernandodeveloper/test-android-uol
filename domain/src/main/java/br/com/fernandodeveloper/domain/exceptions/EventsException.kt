package br.com.fernandodeveloper.domain.exceptions

sealed class EventsException : Exception() {
    object EmptyListException : EventsException()
}
