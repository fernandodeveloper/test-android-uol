package br.com.fernandodeveloper.domain.exceptions

sealed class CheckinException: Exception(){
    object EmptyEmailException: CheckinException()
    object InvalidEmailException: CheckinException()
    object EmptyNameException: CheckinException()
}
