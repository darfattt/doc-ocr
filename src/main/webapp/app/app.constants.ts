// These constants are injected via webpack DefinePlugin variables.
// You can add more variables in webpack.common.js or in profile specific webpack.<dev|prod>.js files.
// If you change the values in the webpack config files, you need to re run webpack to update the application

declare const __DEBUG_INFO_ENABLED__: boolean;
declare const __VERSION__: string;

export const VERSION = __VERSION__;
export const DEBUG_INFO_ENABLED = __DEBUG_INFO_ENABLED__;
export const DOC_TYPES = ['Pengeluaran Barang', 'Surat Jalan', 'Pernyataan', 'BASTPBPP', 'BASTPBP'];
export const DOC_TYPES_NO = ['001', '002', '003', '004', '005'];
export const DOC_VERIFIED_STATUSES = ['APPROVAL', 'APPROVED'];
export const ALL = 'all';
