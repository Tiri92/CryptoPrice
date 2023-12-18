package thierry.cryptoprice.resultof

// find code list here
// https://fr.wikipedia.org/wiki/Liste_des_codes_HTTP
object HttpStatusCode {
    const val OK = 200
    const val CREATED = 201
    const val ACCEPTED = 202
    const val NOT_AUTHORITATIVE = 203
    const val NO_CONTENT = 204
    const val RESET = 205
    const val PARTIAL = 206
    const val ALREADY_REPORTED = 208
    const val MULT_CHOICE = 300
    const val MOVED_PERM = 301
    const val MOVED_TEMP = 302
    const val SEE_OTHER = 303
    const val NOT_MODIFIED = 304
    const val USE_PROXY = 305
    const val BAD_REQUEST = 400
    const val UNAUTHORIZED = 401
    const val PAYMENT_REQUIRED = 402
    const val FORBIDDEN = 403
    const val NOT_FOUND = 404
    const val BAD_METHOD = 405
    const val NOT_ACCEPTABLE = 406
    const val PROXY_AUTH = 407
    const val CLIENT_TIMEOUT = 408
    const val CONFLICT = 409
    const val GONE = 410
    const val LENGTH_REQUIRED = 411
    const val PRECON_FAILED = 412
    const val ENTITY_TOO_LARGE = 413
    const val REQ_TOO_LONG = 414
    const val UNSUPPORTED_TYPE = 415
    const val MISDIRECT_REQUEST = 421
    const val UNPROCESSABLE_ENTITY_CODE = 422
    const val LOCKED = 423
    const val FAILED_DEPENDENCY = 424
    const val TOO_MANY_REQUEST_CODE = 429
    const val UNAVAILABLE_FOR_LEGAL_REASONS = 451
    const val SERVER_ERROR = 500
    const val INTERNAL_ERROR = 500
    const val NOT_IMPLEMENTED = 501
    const val BAD_GATEWAY = 502
    const val UNAVAILABLE = 503
    const val GATEWAY_TIMEOUT = 504
    const val VERSION = 505
}